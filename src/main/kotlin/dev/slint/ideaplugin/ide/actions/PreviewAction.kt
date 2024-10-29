package dev.slint.ideaplugin.ide.actions

import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.actionSystem.CommonDataKeys.VIRTUAL_FILE
import com.intellij.openapi.components.service
import dev.slint.ideaplugin.ide.services.SlintServerService
import dev.slint.ideaplugin.lang.SlintLanguage
import kotlin.io.path.Path



internal class PreviewAction : AnAction() {

    override fun update(e: AnActionEvent) {
        val psiFile = e.getData(CommonDataKeys.PSI_FILE) ?: return
        e.presentation.isEnabledAndVisible = psiFile.language.isKindOf(SlintLanguage.INSTANCE)
    }

    override fun actionPerformed(e: AnActionEvent) {
        val virtualFile = e.getData(VIRTUAL_FILE) ?: return
        val uriFile = Path(virtualFile.path).toUri()

        val project = e.project ?: return
        val slintServerService = project.service<SlintServerService>()

        slintServerService.previewComponent(uriFile.toString(), "")
    }

    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }
}