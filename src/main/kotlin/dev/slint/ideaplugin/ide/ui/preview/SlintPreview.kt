package dev.slint.ideaplugin.ide.ui.preview

import com.intellij.openapi.Disposable
import com.intellij.ui.jcef.JBCefApp
import com.intellij.ui.jcef.JBCefBrowser
import com.intellij.ui.jcef.JBCefBrowserBuilder
import org.cef.handler.CefRequestHandler
import javax.swing.JComponent

class SlintPreview: Disposable {
    private val ourCefClient = JBCefApp.getInstance().createClient()
    private val browser = JBCefBrowserBuilder().setClient(ourCefClient).build()
    private val requestHandler: CefRequestHandler

    val component: JComponent = browser.component

    companion object {
        private const val HOST_NAME = "localhost"
        private const val PROTOCOL = "http"
        private const val INDEX_PATH = "/index.html"
        private const val SLINT_JS_PREVIEW_PATH = "/slint_lsp_wasm.js"
        private const val SLINT_WASM_PREVIEW_PATH = "/slint_lsp_wasm_bg.wasm"
    }

    init {
        requestHandler = CefLocalRequestHandler(PROTOCOL, HOST_NAME)
        requestHandler.addResource(INDEX_PATH) {
            javaClass.getResourceAsStream("/wasmPreview/index.html")?.let {
                CefStreamResourceHandler(it, "text/html", this@SlintPreview)
            }
        }

        requestHandler.addResource(SLINT_JS_PREVIEW_PATH) {
            javaClass.getResourceAsStream("/wasmPreview/slint_lsp_wasm.js")?.let {
                CefStreamResourceHandler(it, "text/javascript", this@SlintPreview)
            }
        }

        requestHandler.addResource(SLINT_WASM_PREVIEW_PATH) {
            javaClass.getResourceAsStream("/wasmPreview/slint_lsp_wasm_bg.wasm")?.let {
                CefStreamResourceHandler(it, "application/wasm", this@SlintPreview)
            }
        }

        ourCefClient.addRequestHandler(requestHandler, browser.cefBrowser)
        ourCefClient.addDisplayHandler(ConsoleMessageHandler(), browser.cefBrowser)

        browser.loadURL("http://localhost/index.html")
    }

    override fun dispose() {
        browser.cefBrowser.client.removeRequestHandler()
    }
}