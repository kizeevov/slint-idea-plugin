package dev.slint.ideaplugin.ide.lsp

import com.intellij.platform.lsp.api.Lsp4jClient
import com.intellij.platform.lsp.api.LspServerNotificationsHandler
import org.eclipse.lsp4j.jsonrpc.services.JsonNotification

class SlintLspClient(
    serverNotificationsHandler: LspServerNotificationsHandler
) : Lsp4jClient(serverNotificationsHandler) {
    @JsonNotification("experimental/serverStatus")
    fun serverStatus(status: Any?) {
    }
}