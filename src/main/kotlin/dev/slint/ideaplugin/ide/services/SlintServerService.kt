package dev.slint.ideaplugin.ide.services

import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project
import com.intellij.platform.lsp.api.LspServer
import com.intellij.platform.lsp.api.LspServerManager
import dev.slint.ideaplugin.SlintBundle
import dev.slint.ideaplugin.ide.lsp.SlintLanguageServer
import dev.slint.ideaplugin.ide.lsp.SlintLspServerSupportProvider

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

    fun getServers(): List<LspServer> {
        val servers = LspServerManager.getInstance(project)
            .getServersForProvider(SlintLspServerSupportProvider::class.java)

        return servers.filter { it.lsp4jServer is SlintLanguageServer }
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