package dev.slint.ideaplugin.ide.editor.preview

import com.intellij.ui.jcef.JBCefBrowser
import com.intellij.ui.jcef.JBCefBrowserBase
import com.intellij.ui.jcef.JBCefJSQuery
import org.cef.browser.CefBrowser
import org.cef.browser.CefFrame
import org.cef.handler.CefLoadHandler
import org.cef.network.CefRequest

internal class SlintLoadHandler(onShowUi: () -> Unit,  browser: JBCefBrowser): CefLoadHandler {
    private val showSlintUiQuery = JBCefJSQuery.create(browser as JBCefBrowserBase)

    init {
        showSlintUiQuery.addHandler { _ ->
            onShowUi()
            null
        }
    }

    override fun onLoadingStateChange(
        browser: CefBrowser?,
        isLoading: Boolean,
        canGoBack: Boolean,
        canGoForward: Boolean
    ) {}

    override fun onLoadStart(browser: CefBrowser?, frame: CefFrame?, transitionType: CefRequest.TransitionType?) {}

    override fun onLoadEnd(browser: CefBrowser?, frame: CefFrame?, httpStatusCode: Int) {
        browser?.executeJavaScript("""
            window.addEventListener('slint.showUi', function (e) {
                ${showSlintUiQuery.inject("")}
            });
        """.trimIndent(), browser.url, 0)
    }

    override fun onLoadError(
        browser: CefBrowser?,
        frame: CefFrame?,
        errorCode: CefLoadHandler.ErrorCode?,
        errorText: String?,
        failedUrl: String?
    ) {}
}