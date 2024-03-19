package dev.slint.ideaplugin.ide.formatter

import com.intellij.formatting.service.AsyncDocumentFormattingService
import com.intellij.formatting.service.AsyncFormattingRequest
import com.intellij.formatting.service.FormattingService
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.components.service
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.psi.PsiFile
import com.jetbrains.rd.util.enumSetOf
import dev.slint.ideaplugin.ide.services.FileEditorService
import dev.slint.ideaplugin.ide.services.SlintServerService
import dev.slint.ideaplugin.lang.psi.SlintFile
import org.eclipse.lsp4j.DocumentFormattingParams
import org.eclipse.lsp4j.FormattingOptions
import org.eclipse.lsp4j.TextDocumentIdentifier
import kotlin.io.path.Path

class SlintLspFormattingService : AsyncDocumentFormattingService() {
    override fun getFeatures(): Set<FormattingService.Feature> = FEATURES

    override fun canFormat(file: PsiFile): Boolean = file is SlintFile

    override fun createFormattingTask(request: AsyncFormattingRequest): FormattingTask? {
        val context = request.context
        val project = context.project
        val file = context.virtualFile ?: return null

        val uriFile = Path(file.path).toUri()

        val params = DocumentFormattingParams(
            TextDocumentIdentifier(uriFile.toString()),
            FormattingOptions(4, true)
        )

        val server = project.service<SlintServerService>().getServers().firstOrNull() ?: return null
        val fileEditorManager = FileEditorManager.getInstance(project)
        val editor = fileEditorManager.selectedTextEditor ?: return null

        return object : FormattingTask {
            override fun run() {
                val edits = server.lsp4jServer.textDocumentService.formatting(params).join()
                edits.forEach { textEdit ->
                    WriteCommandAction.runWriteCommandAction(project) {
                        FileEditorService.applyTextChanges(textEdit, editor)
                    }
                }
            }

            override fun cancel(): Boolean {
                return true
            }

        }
    }

    override fun getNotificationGroupId(): String = "Slint Plugin"

    override fun getName(): String = "slint-lsp"

    companion object {
        private val FEATURES: Set<FormattingService.Feature> = enumSetOf()
    }
}