package dev.slint.ideaplugin.ide.editor.preview

import com.intellij.ide.plugins.PluginManager
import com.intellij.openapi.Disposable
import com.intellij.openapi.extensions.PluginId
import org.cef.browser.CefBrowser
import org.cef.browser.CefFrame
import org.cef.callback.CefCallback
import org.cef.handler.*
import org.cef.misc.BoolRef
import org.cef.network.CefRequest
import java.io.File
import java.net.URL
import java.nio.file.Path

internal class SlintWasmRequestHandler(parent: Disposable) : CefLocalRequestHandler(PROTOCOL, HOST_NAME) {
    companion object {
        private const val HOST_NAME = "localhost"
        private const val PROTOCOL = "http"
        private const val INDEX_PATH = "/index.html"
        private const val SLINT_JS_PREVIEW_PATH = "/slint_lsp_wasm.js"
        private const val SLINT_WASM_PREVIEW_PATH = "/slint_lsp_wasm_bg.wasm"
    }

    init {
        addResource(INDEX_PATH) {
            resolveFile("index.html")?.let {
                CefStreamResourceHandler(it.inputStream(), "text/html", parent)
            }
        }

        addResource(SLINT_JS_PREVIEW_PATH) {
            resolveFile("slint_lsp_wasm.js")?.let {
                CefStreamResourceHandler(it.inputStream(), "text/javascript", parent)
            }
        }

        addResource(SLINT_WASM_PREVIEW_PATH) {
            resolveFile("slint_lsp_wasm_bg.wasm")?.let {
                CefStreamResourceHandler(it.inputStream(), "application/wasm", parent)
            }
        }
    }

    private fun resolveFile(path: String): File? {
        val pluginManager = PluginManager
            .getInstance()
            .findEnabledPlugin(PluginId.getId(dev.slint.ideaplugin.SLINT_PLUGIN_ID)) ?: return null

        val filePath = pluginManager
            .pluginPath
            .resolve("language-server/wasm")
            .resolve(path)

        print(filePath)

        return filePath.toFile()
    }
}

private typealias CefResourceProvider = () -> CefResourceHandler?

internal open class CefLocalRequestHandler(
    private val protocol: String,
    private val authority: String
) : CefRequestHandlerAdapter() {
    private val resources: MutableMap<String, CefResourceProvider> = HashMap()

    private val rejectingResourceHandler: CefResourceHandler = object : CefResourceHandlerAdapter() {
        override fun processRequest(request: CefRequest, callback: CefCallback): Boolean {
            callback.cancel()
            return false
        }
    }

    private val resourceRequestHandler = object : CefResourceRequestHandlerAdapter() {
        override fun getResourceHandler(
            browser: CefBrowser?,
            frame: CefFrame?,
            request: CefRequest
        ): CefResourceHandler {
            val url = URL(request.url)
            url.protocol
            if (!url.protocol.equals(protocol) || !url.authority.equals(authority)) {
                return rejectingResourceHandler
            }
            return try {
                resources[url.path]?.let { it() } ?: rejectingResourceHandler
            } catch (e: RuntimeException) {
                rejectingResourceHandler
            }
        }
    }

    fun addResource(resourcePath: String, resourceProvider: CefResourceProvider) {
        resources[resourcePath] = resourceProvider
    }

    override fun getResourceRequestHandler(
        browser: CefBrowser?,
        frame: CefFrame?,
        request: CefRequest?,
        isNavigation: Boolean,
        isDownload: Boolean,
        requestInitiator: String?,
        disableDefaultHandling: BoolRef?
    ): CefResourceRequestHandler {
        return resourceRequestHandler
    }
}