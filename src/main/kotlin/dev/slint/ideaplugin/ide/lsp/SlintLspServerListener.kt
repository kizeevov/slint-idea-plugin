package dev.slint.ideaplugin.ide.lsp

import com.intellij.platform.lsp.api.LspServerListener
import org.eclipse.lsp4j.InitializeResult
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.project.Project
import com.intellij.platform.lsp.api.LspServer
import dev.slint.ideaplugin.ide.lsp.requests.LoadFileRequest

private val LOG = logger<SlintLspServerListener>()

class SlintLspServerListener(val project: Project) : LspServerListener {
    override fun serverInitialized(params: InitializeResult) {
        super.serverInitialized(params)

        val servers = SlintLspServer.getInstances(project)
        if (servers.isEmpty()) {
            return
        }

        val path = "./projects/tmp/slint-plugin-test/";
        var request = LoadFileRequest(servers.first(), path)
        val response = (servers.first() as LspServer).requestExecutor.sendRequestSync(request) ?: return
    }
}