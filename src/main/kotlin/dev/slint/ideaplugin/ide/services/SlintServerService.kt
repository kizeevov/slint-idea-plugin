package dev.slint.ideaplugin.ide.services

import com.google.gson.JsonElement
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.platform.lsp.api.LspServer
import com.intellij.platform.lsp.api.LspServerManager
import com.intellij.platform.lsp.api.LspServerState
import dev.slint.ideaplugin.ide.lsp.SlintLspServer
import dev.slint.ideaplugin.ide.lsp.SlintLspServerSupportProvider
import dev.slint.ideaplugin.ide.preview.SlintPreviewService
import org.eclipse.lsp4j.*

@Service(Service.Level.PROJECT)
@Suppress("UnstableApiUsage")
class SlintServerService(private val project: Project) {
    fun restartServer() {
        LspServerManager.getInstance(project)
            .stopAndRestartIfNeeded(SlintLspServerSupportProvider::class.java)
    }

    fun previewComponent(path: String, component: String) {
        val server = getActiveServer() ?: return

        project.service<SlintPreviewService>().updatePreviewTarget()

        server.sendRequestSync(2_000) {
            it.workspaceService.executeCommand(
                ExecuteCommandParams(
                    "slint/showPreview",
                    listOf(path, component)
                )
            )
        }
    }

    fun sendPreviewMessage(message: JsonElement): Boolean {
        val server = getActiveServer() ?: return false

        server.sendNotification {
            (it as SlintLspServer).previewToLsp(message)
        }
        return true
    }

    private fun getActiveServer(): LspServer? {
        val servers = LspServerManager.getInstance(project)
            .getServersForProvider(SlintLspServerSupportProvider::class.java)

        return servers.firstOrNull { it.state == LspServerState.Running }
    }
}
