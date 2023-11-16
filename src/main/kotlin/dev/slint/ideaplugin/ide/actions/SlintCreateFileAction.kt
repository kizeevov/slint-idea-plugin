package dev.slint.ideaplugin.ide.actions

import com.intellij.ide.actions.CreateFileFromTemplateAction
import com.intellij.ide.actions.CreateFileFromTemplateDialog
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.client.currentSession
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.psi.PsiDirectory
import dev.slint.ideaplugin.SlintBundle
import dev.slint.ideaplugin.SlintIcons
import org.jetbrains.annotations.Nls

class SlintCreateFileAction : CreateFileFromTemplateAction(CAPTION, "", SlintIcons.SLINT),
    DumbAware {

    override fun getActionName(directory: PsiDirectory?, newName: String, templateName: String?): String = CAPTION

    override fun isAvailable(dataContext: DataContext): Boolean {
        if (!super.isAvailable(dataContext)) return false
        CommonDataKeys.PROJECT.getData(dataContext) ?: return false
        return true
    }

    override fun buildDialog(project: Project, directory: PsiDirectory, builder: CreateFileFromTemplateDialog.Builder) {
        builder.setTitle(CAPTION)
            .addKind(SlintBundle.message("list.item.empty.file"), SlintIcons.SLINT, "Slint File")
    }

    private companion object {
        @Nls
        private val CAPTION = SlintBundle.message("slint.file")
    }
}