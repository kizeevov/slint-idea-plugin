package dev.slint.ideaplugin.ide.editor.preview

import org.cef.CefSettings
import org.cef.browser.CefBrowser
import org.cef.handler.CefDisplayHandlerAdapter
import org.slf4j.LoggerFactory

class ConsoleMessageHandler : CefDisplayHandlerAdapter() {
    private val LOG = LoggerFactory.getLogger(this::class.java);

    override fun onConsoleMessage(
        browser: CefBrowser?,
        level: CefSettings.LogSeverity?,
        message: String?,
        source: String?,
        line: Int
    ): Boolean {
        println("$source:$line - $message")
        LOG.info("$source:$line - $message")
        return true
    }
}