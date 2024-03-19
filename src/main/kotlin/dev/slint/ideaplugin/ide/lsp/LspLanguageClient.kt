package dev.slint.ideaplugin.ide.lsp

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.LogicalPosition
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.OpenFileDescriptor
import com.intellij.openapi.project.Project
import com.intellij.platform.lsp.api.Lsp4jClient
import com.intellij.platform.lsp.api.LspServerNotificationsHandler
import org.eclipse.lsp4j.*
import org.eclipse.lsp4j.jsonrpc.services.JsonNotification
import java.util.concurrent.CompletableFuture

class LspLanguageClient(serverNotificationsHandler: LspServerNotificationsHandler, project: Project)
    : Lsp4jClient(LspServerNotificationsMiddleware(serverNotificationsHandler, project)) {
    @JsonNotification("experimental/serverStatus")
    fun serverStatus(status: Any?) {}
}

@Suppress("OverrideOnly")
class LspServerNotificationsMiddleware(
    private val serverNotificationsHandler: LspServerNotificationsHandler,
    project: Project
): LspServerNotificationsHandler {

    private val fileEditorService = FileEditorService(project)

    override fun applyEdit(params: ApplyWorkspaceEditParams): CompletableFuture<ApplyWorkspaceEditResponse> {
        fileEditorService.applyEdit(params)

        // return serverNotificationsHandler.applyEdit(params)
        return CompletableFuture.supplyAsync {
            null
        }
    }

    override fun configuration(params: ConfigurationParams): CompletableFuture<List<Any?>> {
        return serverNotificationsHandler.configuration(params)
    }

    override fun createProgress(params: WorkDoneProgressCreateParams): CompletableFuture<Void> {
        return serverNotificationsHandler.createProgress(params)
    }

    override fun logMessage(params: MessageParams) {
        return serverNotificationsHandler.logMessage(params)
    }

    override fun logTrace(params: LogTraceParams) {
        return serverNotificationsHandler.logTrace(params)
    }

    override fun notifyProgress(params: ProgressParams) {
        return serverNotificationsHandler.notifyProgress(params)
    }

    override fun publishDiagnostics(params: PublishDiagnosticsParams) {
        return serverNotificationsHandler.publishDiagnostics(params)
    }

    override fun refreshCodeLenses(): CompletableFuture<Void> {
        return serverNotificationsHandler.refreshCodeLenses()
    }

    override fun refreshDiagnostics(): CompletableFuture<Void> {
        return serverNotificationsHandler.refreshDiagnostics()
    }

    override fun refreshInlayHints(): CompletableFuture<Void> {
        return serverNotificationsHandler.refreshInlayHints()
    }

    override fun refreshInlineValues(): CompletableFuture<Void> {
        return serverNotificationsHandler.refreshInlineValues()
    }

    override fun refreshSemanticTokens(): CompletableFuture<Void> {
        return serverNotificationsHandler.refreshSemanticTokens()
    }

    override fun registerCapability(params: RegistrationParams): CompletableFuture<Void> {
        return serverNotificationsHandler.registerCapability(params)
    }

    override fun showDocument(params: ShowDocumentParams): CompletableFuture<ShowDocumentResult> {
        fileEditorService.showDocument(params)

        // return serverNotificationsHandler.showDocument(params)
        return CompletableFuture.supplyAsync {
            null
        }
    }

    override fun showMessage(params: MessageParams) {
        return serverNotificationsHandler.showMessage(params)
    }

    override fun showMessageRequest(params: ShowMessageRequestParams): CompletableFuture<MessageActionItem> {
        return serverNotificationsHandler.showMessageRequest(params)
    }

    override fun telemetryEvent(`object`: Any) {
        return serverNotificationsHandler.telemetryEvent(`object`)
    }

    override fun unregisterCapability(params: UnregistrationParams): CompletableFuture<Void> {
        return serverNotificationsHandler.unregisterCapability(params)
    }

    override fun workspaceFolders(): CompletableFuture<List<WorkspaceFolder>> {
        return serverNotificationsHandler.workspaceFolders()
    }
}

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

    private fun applyTextChanges(textEdit: TextEdit, editor: Editor) {
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