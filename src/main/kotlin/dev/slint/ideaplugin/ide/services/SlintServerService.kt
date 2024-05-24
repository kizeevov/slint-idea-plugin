package dev.slint.ideaplugin.ide.services

import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project
import com.intellij.platform.lsp.api.LspServer
import com.intellij.platform.lsp.api.LspServerManager
import com.intellij.platform.lsp.api.LspServerState
import dev.slint.ideaplugin.ide.lsp.SlintLspServerSupportProvider
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

        server.sendRequestSync {
            it.workspaceService.executeCommand(
                ExecuteCommandParams(
                    "slint/showPreview",
                    listOf(path, component)
                )
            )
        }
    }

    private fun getActiveServer(): LspServer? {
        val servers = LspServerManager.getInstance(project)
            .getServersForProvider(SlintLspServerSupportProvider::class.java)

        return servers.firstOrNull { it.state == LspServerState.Running }
    }
}