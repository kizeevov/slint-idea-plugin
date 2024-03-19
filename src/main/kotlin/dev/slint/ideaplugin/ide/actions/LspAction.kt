package dev.slint.ideaplugin.ide.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.service
import com.intellij.platform.lsp.api.LspServer
import dev.slint.ideaplugin.ide.services.SlintServerService
import javax.swing.Icon

abstract class LspAction(text: String, description: String?, icon: Icon?) : AnAction(text, description, icon) {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return

        val servers = project.service<SlintServerService>().getServers()
        if (servers.isEmpty()) {
            return
        }

        actionPerformed(e, servers)
    }

    abstract fun actionPerformed(e: AnActionEvent, servers: List<LspServer>)
}