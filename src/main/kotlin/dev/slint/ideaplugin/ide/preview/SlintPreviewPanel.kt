package dev.slint.ideaplugin.ide.preview

import com.google.gson.JsonElement
import com.google.gson.JsonNull
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.intellij.openapi.Disposable
import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Disposer
import com.intellij.ui.jcef.JBCefBrowser
import com.intellij.ui.jcef.JBCefBrowserBase
import com.intellij.ui.jcef.JBCefJSQuery
import com.intellij.util.concurrency.AppExecutorUtil
import org.cef.CefSettings
import org.cef.browser.CefBrowser
import org.cef.handler.CefDisplayHandlerAdapter
import javax.swing.JComponent

internal class SlintPreviewPanel(private val project: Project) : Disposable {
    private val browser = JBCefBrowser.createBuilder().build()
    private val fromPreviewQuery = JBCefJSQuery.create(browser as JBCefBrowserBase)
    private val resources = SlintPreviewResourceHandler(project, fromPreviewQuery)

    private val executor = AppExecutorUtil.createBoundedApplicationPoolExecutor("Slint Preview", 1)

    @Volatile
    var isReady = false
        private set

    @Volatile
    private var isDisposed = false

    val component: JComponent
        get() = browser.component

    init {
        Disposer.register(this, browser)

        browser.jbCefClient.addRequestHandler(resources, browser.cefBrowser)
        browser.jbCefClient.addDisplayHandler(ConsoleMessageHandler, browser.cefBrowser)
        fromPreviewQuery.addHandler { request ->
            executor.execute { if (!isDisposed) handleMessageFromPreview(request) }
            null
        }

        browser.loadURL(SlintPreviewResourceHandler.INDEX_URL)
    }

    fun postToPreview(message: JsonElement) {
        val payload = JsonObject().apply {
            addProperty("command", "slint/lsp_to_preview")
            add("params", message)
        }
        sendToPage(payload)
    }

    override fun dispose() {
        isReady = false
        isDisposed = true
    }

    private fun handleMessageFromPreview(request: String) {
        val message = runCatching { JsonParser.parseString(request).asJsonObject }.getOrElse {
            LOG.warn("Invalid message from Slint preview: $request", it)
            return
        }
        val previewService = project.service<SlintPreviewService>()

        when (message.get("command")?.asString) {
            "slint/preview_to_lsp" -> message.get("params")?.let { previewService.sendToLsp(it) }
            "map_url" -> {
                val original = message.get("url")?.asString ?: return
                val response = JsonObject().apply {
                    addProperty("command", "map_response")
                    addProperty("original", original)
                    val mapped = resources.mapUrl(original)
                    if (mapped != null) addProperty("mapped", mapped) else add("mapped", JsonNull.INSTANCE)
                }
                sendToPage(response)
            }
            "preview_ready" -> {
                isReady = true
                previewService.onPreviewReady()
            }
        }
    }

    private fun sendToPage(payload: JsonObject) {
        val script = "window.slintIdeReceive && window.slintIdeReceive($payload);"
        browser.cefBrowser.executeJavaScript(script, SlintPreviewResourceHandler.INDEX_URL, 0)
    }

    private object ConsoleMessageHandler : CefDisplayHandlerAdapter() {
        override fun onConsoleMessage(
            browser: CefBrowser?,
            level: CefSettings.LogSeverity?,
            message: String?,
            source: String?,
            line: Int
        ): Boolean {
            val text = "$source:$line - $message"
            when (level) {
                CefSettings.LogSeverity.LOGSEVERITY_ERROR,
                CefSettings.LogSeverity.LOGSEVERITY_FATAL -> LOG.warn(text)
                else -> LOG.debug(text)
            }
            return false
        }
    }

    companion object {
        private val LOG = logger<SlintPreviewPanel>()
    }
}
