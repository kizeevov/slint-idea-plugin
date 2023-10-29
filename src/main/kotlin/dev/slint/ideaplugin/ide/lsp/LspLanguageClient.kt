package dev.slint.ideaplugin.ide.lsp

import com.intellij.platform.lsp.api.Lsp4jClient
import com.intellij.platform.lsp.api.LspServerNotificationsHandler
import org.eclipse.lsp4j.MessageActionItem
import org.eclipse.lsp4j.MessageParams
import org.eclipse.lsp4j.PublishDiagnosticsParams
import org.eclipse.lsp4j.ShowMessageRequestParams
import org.eclipse.lsp4j.jsonrpc.services.JsonNotification
import org.eclipse.lsp4j.services.LanguageClient
import java.util.concurrent.CompletableFuture

class LspLanguageClient(serverNotificationsHandler: LspServerNotificationsHandler) :
        Lsp4jClient(serverNotificationsHandler)
{
    @JsonNotification("experimental/serverStatus")
    fun serverStatus(params: Any) {
        println("status")
        println(params)
    }
//    override fun telemetryEvent(p0: Any?) {
//        TODO("Not yet implemented")
//    }
//
//    override fun publishDiagnostics(p0: PublishDiagnosticsParams?) {
//        TODO("Not yet implemented")
//    }
//
//    override fun showMessage(p0: MessageParams?) {
//        TODO("Not yet implemented")
//    }
//
//    override fun showMessageRequest(p0: ShowMessageRequestParams?): CompletableFuture<MessageActionItem> {
//        TODO("Not yet implemented")
//    }
//
//    override fun logMessage(p0: MessageParams?) {
//        TODO("Not yet implemented")
//    }
}