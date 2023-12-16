package dev.slint.ideaplugin.ide.lsp.requests

import com.intellij.platform.lsp.api.LspServer
import com.intellij.platform.lsp.api.requests.LspRequest
import org.eclipse.lsp4j.ExecuteCommandParams
import java.util.concurrent.CompletableFuture

class PreviewMessageRequest(private val server: LspServer, private val path: String, private val component: String) : LspRequest<Any, Any>(server) {
    override fun preprocessResponse(serverResponse: Any): Any {
        return serverResponse
    }

    override fun sendRequest(): CompletableFuture<Any> {
        return server.lsp4jServer.workspaceService
                .executeCommand(
                        ExecuteCommandParams(
                                "slint/showPreview",
                                listOf(path, component)
                        )
                )
    }
}