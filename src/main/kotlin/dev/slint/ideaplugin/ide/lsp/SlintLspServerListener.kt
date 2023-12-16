package dev.slint.ideaplugin.ide.lsp

import com.intellij.platform.lsp.api.LspServerListener
import org.eclipse.lsp4j.InitializeResult
import com.intellij.openapi.project.Project

class SlintLspServerListener(val project: Project) : LspServerListener {
    override fun serverInitialized(params: InitializeResult) {
        super.serverInitialized(params)
    }
}