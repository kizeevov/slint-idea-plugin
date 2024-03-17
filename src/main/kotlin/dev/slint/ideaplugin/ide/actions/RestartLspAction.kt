package dev.slint.ideaplugin.ide.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.service
import com.intellij.openapi.project.DumbAware
import dev.slint.ideaplugin.ide.services.SlintServerService

class RestartLspAction: AnAction(), DumbAware {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val slintServerService = project.service<SlintServerService>()
        slintServerService.restartServer()
    }

    companion object {
        const val ID: String = "Slint.RestartLspAction"
    }
}