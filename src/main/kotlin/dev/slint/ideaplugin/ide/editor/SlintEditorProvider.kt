package dev.slint.ideaplugin.ide.editor

import com.intellij.openapi.fileEditor.*
import com.intellij.openapi.fileEditor.impl.text.TextEditorProvider
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import dev.slint.ideaplugin.ide.editor.preview.SLINT_EDITOR_NAME
import dev.slint.ideaplugin.ide.editor.preview.SlintPreview
import dev.slint.ideaplugin.ide.settings.SlintSettingsState
import dev.slint.ideaplugin.lang.isSlint

class SlintEditorProvider : FileEditorProvider, DumbAware {
    override fun accept(project: Project, file: VirtualFile): Boolean = file.isSlint

    override fun createEditor(project: Project, file: VirtualFile): FileEditor {
        val editor = TextEditorProvider.getInstance().createEditor(project, file) as TextEditor
        val settingState = SlintSettingsState.getInstance().lspSettings
        return if (settingState.providedByEditor) {
            val preview = SlintPreview(project, file, editor.editor.document)
            TextEditorWithPreview(editor, preview, SLINT_EDITOR_NAME, TextEditorWithPreview.Layout.SHOW_EDITOR_AND_PREVIEW)
        } else {
            editor
        }
    }

    override fun getEditorTypeId(): String = SLINT_EDITOR_NAME

    override fun getPolicy(): FileEditorPolicy = FileEditorPolicy.HIDE_DEFAULT_EDITOR
}