package dev.slint.ideaplugin.ide.preview

import com.google.gson.JsonPrimitive
import com.intellij.openapi.application.ReadAction
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ProjectFileIndex
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.ui.jcef.JBCefJSQuery
import dev.slint.ideaplugin.ide.lsp.SlintPluginPaths
import dev.slint.ideaplugin.ide.settings.SlintSettingsState
import dev.slint.ideaplugin.ide.settings.SlintStyle
import org.cef.browser.CefBrowser
import org.cef.browser.CefFrame
import org.cef.callback.CefCallback
import org.cef.handler.CefRequestHandlerAdapter
import org.cef.handler.CefResourceHandler
import org.cef.handler.CefResourceRequestHandler
import org.cef.handler.CefResourceRequestHandlerAdapter
import org.cef.misc.BoolRef
import org.cef.misc.IntRef
import org.cef.misc.StringRef
import org.cef.network.CefRequest
import org.cef.network.CefResponse
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.InputStream
import java.net.URI
import java.net.URLConnection
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.file.Files
import java.nio.file.Path
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger
import kotlin.io.path.isRegularFile

internal class SlintPreviewResourceHandler(
    private val project: Project,
    private val fromPreviewQuery: JBCefJSQuery,
) : CefRequestHandlerAdapter() {
    private val mappedFiles = ConcurrentHashMap<String, Path>()
    private val mappedUrls = ConcurrentHashMap<Path, String>()
    private val nextFileId = AtomicInteger()

    private val resourceRequestHandler = object : CefResourceRequestHandlerAdapter() {
        override fun getResourceHandler(browser: CefBrowser?, frame: CefFrame?, request: CefRequest): CefResourceHandler =
            try {
                resolve(request.url) ?: RejectingResourceHandler
            } catch (e: Exception) {
                LOG.warn("Failed to serve ${request.url}", e)
                RejectingResourceHandler
            }
    }

    override fun getResourceRequestHandler(
        browser: CefBrowser?,
        frame: CefFrame?,
        request: CefRequest?,
        isNavigation: Boolean,
        isDownload: Boolean,
        requestInitiator: String?,
        disableDefaultHandling: BoolRef?
    ): CefResourceRequestHandler = resourceRequestHandler

    fun mapUrl(url: String): String? {
        val path = runCatching { Path.of(URI(url)).normalize() }.getOrNull() ?: return null
        if (!path.isRegularFile() || !isAllowed(path)) return null

        return mappedUrls.computeIfAbsent(path) {
            val id = nextFileId.incrementAndGet().toString()
            mappedFiles[id] = path
            "$ORIGIN$FILE_PREFIX$id/${URLEncoder.encode(path.fileName.toString(), Charsets.UTF_8)}"
        }
    }

    private fun isAllowed(path: Path): Boolean {
        val includePaths = SlintSettingsState.getInstance().lspSettings.includePaths
        if (includePaths.any { runCatching { path.startsWith(Path.of(it).normalize()) }.getOrDefault(false) }) {
            return true
        }

        val file = LocalFileSystem.getInstance().findFileByNioFile(path) ?: return false
        return ReadAction.nonBlocking<Boolean> {
            val index = ProjectFileIndex.getInstance(project)
            index.isInContent(file) || index.isInLibrary(file)
        }.executeSynchronously()
    }

    private fun resolve(url: String): CefResourceHandler? {
        val uri = URI(url)
        if (uri.scheme != "http" || uri.authority != HOST) return null

        val path = uri.rawPath ?: return null
        return when {
            path == INDEX_PATH -> StreamResourceHandler(ByteArrayInputStream(indexHtml().toByteArray()), "text/html")
            path == JS_PATH -> fileHandler(SlintPluginPaths.wasmDir.resolve(JS_FILE), "text/javascript")
            path == WASM_PATH -> fileHandler(SlintPluginPaths.wasmDir.resolve(WASM_FILE), "application/wasm")
            path.startsWith(FILE_PREFIX) -> {
                val id = path.removePrefix(FILE_PREFIX).substringBefore('/')
                val file = mappedFiles[id] ?: return null
                val name = URLDecoder.decode(path.substringAfterLast('/'), Charsets.UTF_8)
                fileHandler(file, URLConnection.guessContentTypeFromName(name) ?: guessMimeType(name))
            }
            else -> null
        }
    }

    private fun fileHandler(path: Path, mimeType: String): CefResourceHandler? {
        if (!path.isRegularFile()) {
            LOG.warn("Slint preview resource not found: $path")
            return null
        }
        return StreamResourceHandler(Files.newInputStream(path), mimeType)
    }

    private fun indexHtml(): String {
        val template = javaClass.getResourceAsStream(INDEX_RESOURCE)?.use { it.readBytes().toString(Charsets.UTF_8) }
            ?: error("$INDEX_RESOURCE is missing")
        val style = SlintSettingsState.getInstance().lspSettings.style
            .takeIf { it != SlintStyle.DEFAULT }?.toString() ?: ""

        return template
            .replace("/*SEND_TO_IDE*/", fromPreviewQuery.inject("request"))
            .replace("\"/*DEFAULT_STYLE*/\"", JsonPrimitive(style).toString())
    }

    private class StreamResourceHandler(private val stream: InputStream, private val mimeType: String) : CefResourceHandler {
        override fun processRequest(request: CefRequest, callback: CefCallback): Boolean {
            callback.Continue()
            return true
        }

        override fun getResponseHeaders(response: CefResponse, responseLength: IntRef, redirectUrl: StringRef) {
            response.mimeType = mimeType
            response.status = 200
            responseLength.set(-1)
        }

        override fun readResponse(dataOut: ByteArray, bytesToRead: Int, bytesRead: IntRef, callback: CefCallback): Boolean {
            try {
                val read = stream.read(dataOut, 0, bytesToRead)
                if (read != -1) {
                    bytesRead.set(read)
                    return true
                }
            } catch (e: IOException) {
                LOG.warn("Failed to read Slint preview resource", e)
            }
            bytesRead.set(0)
            close()
            return false
        }

        override fun cancel() {
            close()
        }

        private fun close() {
            try {
                stream.close()
            } catch (e: IOException) {
                LOG.warn("Failed to close the stream", e)
            }
        }
    }

    private object RejectingResourceHandler : CefResourceHandler {
        override fun processRequest(request: CefRequest, callback: CefCallback): Boolean {
            callback.cancel()
            return false
        }

        override fun getResponseHeaders(response: CefResponse, responseLength: IntRef, redirectUrl: StringRef) {}

        override fun readResponse(dataOut: ByteArray, bytesToRead: Int, bytesRead: IntRef, callback: CefCallback) = false

        override fun cancel() {}
    }

    companion object {
        private val LOG = logger<SlintPreviewResourceHandler>()

        private const val HOST = "slint-preview"
        private const val ORIGIN = "http://$HOST"
        private const val INDEX_PATH = "/index.html"
        private const val INDEX_RESOURCE = "/wasmPreview/index.html"
        private const val JS_FILE = "slint_lsp_wasm.js"
        private const val WASM_FILE = "slint_lsp_wasm_bg.wasm"
        private const val JS_PATH = "/$JS_FILE"
        private const val WASM_PATH = "/$WASM_FILE"
        private const val FILE_PREFIX = "/file/"

        const val INDEX_URL = "$ORIGIN$INDEX_PATH"

        private fun guessMimeType(name: String): String = when (name.substringAfterLast('.').lowercase()) {
            "svg" -> "image/svg+xml"
            "ttf" -> "font/ttf"
            "otf" -> "font/otf"
            "woff" -> "font/woff"
            "woff2" -> "font/woff2"
            "webp" -> "image/webp"
            else -> "application/octet-stream"
        }
    }
}
