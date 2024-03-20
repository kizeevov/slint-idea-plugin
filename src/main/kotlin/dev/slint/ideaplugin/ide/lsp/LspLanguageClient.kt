package dev.slint.ideaplugin.ide.lsp

import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.platform.lsp.api.Lsp4jClient
import com.intellij.platform.lsp.api.LspServerNotificationsHandler
import dev.slint.ideaplugin.ide.services.FileEditorService
import org.eclipse.lsp4j.*
import org.eclipse.lsp4j.jsonrpc.services.JsonNotification
import java.util.concurrent.CompletableFuture

class LspLanguageClient(project: Project) :
    Lsp4jClient(LspServerNotificationsMiddleware(project)) {
    @JsonNotification("experimental/serverStatus")
    fun serverStatus(status: Any?) {
    }
}


class LspServerNotificationsMiddleware(
    project: Project
) : LspServerNotificationsHandler {
    private val fileEditorService = project.service<FileEditorService>()

    override fun applyEdit(params: ApplyWorkspaceEditParams): CompletableFuture<ApplyWorkspaceEditResponse> {
        fileEditorService.applyEdit(params)
        return CompletableFuture.supplyAsync {
            ApplyWorkspaceEditResponse(true)
        }
    }

    override fun configuration(params: ConfigurationParams): CompletableFuture<List<Any?>> {
        return CompletableFuture.supplyAsync {
            null
        }
    }

    override fun createProgress(params: WorkDoneProgressCreateParams): CompletableFuture<Void> {
        return CompletableFuture.supplyAsync {
            null
        }
    }

    override fun logMessage(params: MessageParams) {}

    override fun logTrace(params: LogTraceParams) {}

    override fun notifyProgress(params: ProgressParams) {}

    override fun publishDiagnostics(params: PublishDiagnosticsParams) {}

    override fun refreshCodeLenses(): CompletableFuture<Void> {
        return CompletableFuture.supplyAsync {
            null
        }
    }

    override fun refreshDiagnostics(): CompletableFuture<Void> {
        return CompletableFuture.supplyAsync {
            null
        }
    }

    override fun refreshInlayHints(): CompletableFuture<Void> {
        return CompletableFuture.supplyAsync {
            null
        }
    }

    override fun refreshInlineValues(): CompletableFuture<Void> {
        return CompletableFuture.supplyAsync {
            null
        }
    }

    override fun refreshSemanticTokens(): CompletableFuture<Void> {
        return CompletableFuture.supplyAsync {
            null
        }
    }

    override fun registerCapability(params: RegistrationParams): CompletableFuture<Void> {
        return CompletableFuture.supplyAsync {
            null
        }
    }

    override fun showDocument(params: ShowDocumentParams): CompletableFuture<ShowDocumentResult> {
        fileEditorService.showDocument(params)
        return CompletableFuture.supplyAsync {
            ShowDocumentResult(true)
        }
    }

    override fun showMessage(params: MessageParams) {}

    override fun showMessageRequest(params: ShowMessageRequestParams): CompletableFuture<MessageActionItem> {
        return CompletableFuture.supplyAsync {
            null
        }
    }

    override fun telemetryEvent(`object`: Any) {}

    override fun unregisterCapability(params: UnregistrationParams): CompletableFuture<Void> {
        return CompletableFuture.supplyAsync {
            null
        }
    }

    override fun workspaceFolders(): CompletableFuture<List<WorkspaceFolder>> {
        return CompletableFuture.supplyAsync {
            null
        }
    }
}