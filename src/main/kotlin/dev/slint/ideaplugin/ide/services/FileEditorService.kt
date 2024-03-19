package dev.slint.ideaplugin.ide.services

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.components.Service
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.LogicalPosition
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.OpenFileDescriptor
import com.intellij.openapi.project.Project
import org.eclipse.lsp4j.ApplyWorkspaceEditParams
import org.eclipse.lsp4j.ShowDocumentParams
import org.eclipse.lsp4j.TextDocumentEdit
import org.eclipse.lsp4j.TextEdit

@Service(Service.Level.PROJECT)
class FileEditorService(private val project: Project) {
    private val fileEditorManager = FileEditorManager.getInstance(project)

    fun applyEdit(params: ApplyWorkspaceEditParams) {
        params.edit.documentChanges.forEach {
            if (it.isLeft) {
                applyDocumentChanges(it.left)
            }
        }
    }

    fun showDocument(params: ShowDocumentParams) {
        fileEditorManager.openFiles
            .find {
                it.url == params.uri
            }
            ?.let {
                val startPosition = params.selection.start.run {
                    LogicalPosition(line, character)
                }
                val endPosition = params.selection.end.run {
                    LogicalPosition(line, character)
                }

                WriteCommandAction.runWriteCommandAction(project) {
                    fileEditorManager
                        .openTextEditor(OpenFileDescriptor(project, it), true)
                        ?.selectionModel
                        ?.setBlockSelection(startPosition, endPosition)
                }
            }
    }

    private fun applyDocumentChanges(documentChanges: TextDocumentEdit) {
        val fileUrl = documentChanges.textDocument.uri
        fileEditorManager.openFiles
            .find {
                it.url == fileUrl
            }
            ?.let { virtualFile ->
                WriteCommandAction.runWriteCommandAction(project) {
                    fileEditorManager
                        .openTextEditor(OpenFileDescriptor(project, virtualFile), true)
                        ?.let { editor: Editor ->
                            documentChanges.edits.forEach {
                                applyTextChanges(it, editor)
                            }
                        }
                }
            }
    }

    companion object {
        fun applyTextChanges(textEdit: TextEdit, editor: Editor) {
            val startPosition = textEdit.range.start.run {
                LogicalPosition(line, character)
            }
            val endPosition = textEdit.range.end.run {
                LogicalPosition(line, character)
            }

            val startOffset = editor.logicalPositionToOffset(startPosition)
            val endOffset= editor.logicalPositionToOffset(endPosition)

            editor.document.replaceString(startOffset, endOffset, textEdit.newText)
        }
    }
}