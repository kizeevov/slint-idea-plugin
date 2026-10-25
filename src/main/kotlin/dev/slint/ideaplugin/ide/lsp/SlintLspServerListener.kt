package dev.slint.ideaplugin.ide.lsp

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.service
import com.intellij.platform.lsp.api.LspServerListener
import org.eclipse.lsp4j.InitializeResult
import com.intellij.openapi.project.Project
import dev.slint.ideaplugin.ide.preview.SlintPreviewService

class SlintLspServerListener(val project: Project) : LspServerListener {
    override fun serverInitialized(params: InitializeResult) {
        super.serverInitialized(params)

        ApplicationManager.getApplication().executeOnPooledThread {
            project.service<SlintPreviewService>().updatePreviewTarget()
        }
    }
}
