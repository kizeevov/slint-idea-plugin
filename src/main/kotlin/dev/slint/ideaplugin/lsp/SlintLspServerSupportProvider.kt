package dev.slint.ideaplugin.lsp

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.LspServerSupportProvider
import dev.slint.ideaplugin.lang.SlintFileType

class SlintLspServerSupportProvider : LspServerSupportProvider {
    override fun fileOpened(project: Project, file: VirtualFile, serverStarter: LspServerSupportProvider.LspServerStarter) {
        if (file.fileType != SlintFileType) return

//        val node = NodeJsInterpreterManager.getInstance(project).interpreter
//        if (node !is NodeJsLocalInterpreter && node !is WslNodeInterpreter) return

        serverStarter.ensureServerStarted(SlintLspServerDescriptor(project))
    }
}