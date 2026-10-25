package dev.slint.ideaplugin.ide.lsp

import com.google.gson.JsonElement
import org.eclipse.lsp4j.jsonrpc.services.JsonNotification
import org.eclipse.lsp4j.jsonrpc.services.JsonRequest
import org.eclipse.lsp4j.services.LanguageServer
import java.util.concurrent.CompletableFuture

interface SlintLspServer : LanguageServer {
    @JsonRequest("slint/load_file")
    fun loadFile(path:  String): CompletableFuture<String>

    @JsonNotification("slint/preview_to_lsp")
    fun previewToLsp(message: JsonElement)
}
