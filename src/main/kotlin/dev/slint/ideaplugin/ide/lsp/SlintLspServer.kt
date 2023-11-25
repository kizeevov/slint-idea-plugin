package dev.slint.ideaplugin.ide.lsp

import com.intellij.openapi.project.Project
import com.intellij.platform.lsp.api.LspServer
import com.intellij.platform.lsp.api.LspServerDescriptor
import com.intellij.platform.lsp.api.LspServerManager
import com.intellij.platform.lsp.api.LspServerNotificationsHandler
import com.intellij.platform.lsp.api.requests.LspRequestExecutor

@Suppress("UnstableApiUsage")
class SlintLspServer(private val server: LspServer) : LspServer {
    companion object {
        // These should probably be split off into SlintLspManager or something
        private fun getManager(project: Project): LspServerManager {
            return LspServerManager.getInstance(project)
        }

        fun startServersIfNeeded(project: Project) {
            val manager = getManager(project)
            manager.stopAndRestartIfNeeded(SlintLspServerSupportProvider::class.java)
        }

        fun getInstances(project: Project): List<SlintLspServer> {
            val manager = getManager(project)
            val servers = manager.getServersForProvider(SlintLspServerSupportProvider::class.java)
            // There's prolly an easier way to do this but oh well
            return servers.filter { it.lsp4jServer is SlintLanguageServer }.map {
                SlintLspServer(
                        it
                )
            }
        }
    }

    override val descriptor: LspServerDescriptor = server.descriptor
    override val lsp4jServer: SlintLanguageServer = server.lsp4jServer as SlintLanguageServer
    override val project: Project = server.project
    override val requestExecutor: LspRequestExecutor = server.requestExecutor
    override val serverNotificationsHandler: LspServerNotificationsHandler = server.serverNotificationsHandler
}