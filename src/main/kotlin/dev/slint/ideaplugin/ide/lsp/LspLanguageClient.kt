package dev.slint.ideaplugin.ide.lsp

import com.intellij.platform.lsp.api.Lsp4jClient
import com.intellij.platform.lsp.api.LspServerNotificationsHandler
import org.eclipse.lsp4j.jsonrpc.services.JsonNotification

class LspLanguageClient(handler: LspServerNotificationsHandler) : Lsp4jClient(handler) {
    @JsonNotification("experimental/serverStatus")
    fun serverStatus(status: Any?) {
    }
}