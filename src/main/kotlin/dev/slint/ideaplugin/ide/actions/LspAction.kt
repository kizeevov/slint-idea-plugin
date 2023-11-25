package dev.slint.ideaplugin.ide.actions

import com.intellij.json.JsonLanguage
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.psi.util.elementType
import dev.slint.ideaplugin.ide.lsp.SlintLspServer
import dev.slint.ideaplugin.lang.SlintLanguage
import dev.slint.ideaplugin.lang.psi.SlintFileElementType
import javax.swing.Icon


abstract class LspAction(text: String, description: String?, icon: Icon?) : AnAction(text, description, icon) {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return

        val servers = SlintLspServer.getInstances(project)
        if (servers.isEmpty()) {
            return
        }
        actionPerformed(e, servers)
    }

    abstract fun actionPerformed(e: AnActionEvent, servers: List<SlintLspServer>)
}