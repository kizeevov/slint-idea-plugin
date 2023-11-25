package dev.slint.ideaplugin.ide.lsp

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.LspServerSupportProvider
import dev.slint.ideaplugin.lang.SlintFileType

@Suppress("UnstableApiUsage")
class SlintLspServerSupportProvider : LspServerSupportProvider {
    override fun fileOpened(project: Project, file: VirtualFile, serverStarter: LspServerSupportProvider.LspServerStarter) {
        if (file.fileType != SlintFileType) return
        serverStarter.ensureServerStarted(SlintLspServerDescriptor(project))
    }
}