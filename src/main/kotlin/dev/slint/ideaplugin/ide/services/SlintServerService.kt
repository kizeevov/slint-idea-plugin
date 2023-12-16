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
class SlintServerService(private val project: Project) {
    fun restartServer() {
        LspServerManager.getInstance(project)
            .stopAndRestartIfNeeded(SlintLspServerSupportProvider::class.java)
    }

    fun notifyRestart() {
        NotificationGroupManager.getInstance()
            .getNotificationGroup("Slint")
            .createNotification(
                SlintBundle.message("slint.language.server.restarted"),
                "",
                NotificationType.INFORMATION
            )
            .notify(project)
    }

    fun getServers(): List<LspServer> {
        val servers = LspServerManager.getInstance(project)
            .getServersForProvider(SlintLspServerSupportProvider::class.java)

        return servers.filter { it.lsp4jServer is SlintLanguageServer }
    }
}