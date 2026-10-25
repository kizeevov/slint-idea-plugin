package dev.slint.ideaplugin.ide.lsp

import com.google.gson.JsonElement
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.platform.lsp.api.Lsp4jClient
import com.intellij.platform.lsp.api.LspServerNotificationsHandler
import dev.slint.ideaplugin.ide.preview.SlintPreviewService
import org.eclipse.lsp4j.jsonrpc.services.JsonNotification

class SlintLspClient(
    serverNotificationsHandler: LspServerNotificationsHandler,
    private val project: Project,
) : Lsp4jClient(serverNotificationsHandler) {
    @JsonNotification("experimental/serverStatus")
    fun serverStatus(@Suppress("UNUSED_PARAMETER") status: Any?) {
    }

    @JsonNotification("slint/lsp_to_preview")
    fun lspToPreview(message: JsonElement) {
        project.service<SlintPreviewService>().onLspToPreview(message)
    }
}
