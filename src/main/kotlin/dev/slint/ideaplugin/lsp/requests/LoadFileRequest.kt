package dev.slint.ideaplugin.lsp.requests

import com.intellij.platform.lsp.api.requests.LspRequest
import dev.slint.ideaplugin.lsp.SlintLspServer
import java.util.concurrent.CompletableFuture

class LoadFileRequest(private val server: SlintLspServer, private val path: String) : LspRequest<String, String>(server) {
    override fun preprocessResponse(serverResponse: String): String {
        System.out.println("----234234")
        return serverResponse
    }

    override fun sendRequest(): CompletableFuture<String> {
        return server.lsp4jServer.loadFile(path)
    }
}