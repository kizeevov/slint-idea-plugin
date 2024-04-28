package dev.slint.ideaplugin.ide.actions

import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys.VIRTUAL_FILE
import com.intellij.openapi.components.service
import dev.slint.ideaplugin.ide.services.SlintServerService
import kotlin.io.path.Path


internal class PreviewComponentAction(private val componentName: String) :
    AnAction("Show Component Preview", null, AllIcons.Actions.ShowCode) {

    override fun actionPerformed(e: AnActionEvent) {
        val virtualFile = e.getData(VIRTUAL_FILE) ?: return
        val uriFile = Path(virtualFile.path).toUri()

        val project = e.project ?: return
        val slintServerService = project.service<SlintServerService>()

        slintServerService.previewComponent(uriFile.toString(), componentName)
    }
}