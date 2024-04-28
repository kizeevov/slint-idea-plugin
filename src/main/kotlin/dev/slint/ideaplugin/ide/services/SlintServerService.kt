package dev.slint.ideaplugin.ide.services

import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project
import com.intellij.platform.lsp.api.LspServer
import com.intellij.platform.lsp.api.LspServerManager
import com.intellij.platform.lsp.api.LspServerState
import dev.slint.ideaplugin.SlintBundle
import dev.slint.ideaplugin.ide.lsp.SlintLspServerSupportProvider
import org.eclipse.lsp4j.*

@Service(Service.Level.PROJECT)
@Suppress("UnstableApiUsage")
class SlintServerService(private val project: Project) {
    private var status = Status.TERMINATING

    val isRunning get() = status == Status.RUNNING

    val isRestarting get() = status == Status.RESTARTING

    fun setRunningStatus() {
        status = Status.RUNNING
    }

    fun setTerminatingStatus() {
        status = Status.TERMINATING
        notifyTerminating()
    }

    fun restartServer() {
        LspServerManager.getInstance(project)
            .stopAndRestartIfNeeded(SlintLspServerSupportProvider::class.java)

        status = Status.RESTARTING
        notifyRestart()
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

    fun formatting(path: String, tabSize: Int, insertSpaces: Boolean): List<TextEdit>? {
        val server = getActiveServer() ?: return null

        val params = DocumentFormattingParams(
            TextDocumentIdentifier(path),
            FormattingOptions(tabSize, insertSpaces)
        )

        return server.sendRequestSync { it.textDocumentService.formatting(params) }
    }

    private fun getActiveServer(): LspServer? {
        val servers = LspServerManager.getInstance(project)
            .getServersForProvider(SlintLspServerSupportProvider::class.java)

        return servers.firstOrNull { it.state == LspServerState.Running }
    }

    private fun notifyRun() {
        NotificationGroupManager.getInstance()
            .getNotificationGroup("Slint")
            .createNotification(
                SlintBundle.message("slint.language.server.is.running"),
                "",
                NotificationType.INFORMATION
            )
            .notify(project)
    }

    private fun notifyRestart() {
        NotificationGroupManager.getInstance()
            .getNotificationGroup("Slint")
            .createNotification(
                SlintBundle.message("slint.language.server.restarted"),
                "",
                NotificationType.INFORMATION
            )
            .notify(project)
    }

    private fun notifyTerminating() {
        NotificationGroupManager.getInstance()
            .getNotificationGroup("Slint")
            .createNotification(
                SlintBundle.message("slint.language.server.is.stopped"),
                "",
                NotificationType.WARNING
            )
            .notify(project)
    }

    private enum class Status {
        RUNNING,
        RESTARTING,
        TERMINATING
    }
}