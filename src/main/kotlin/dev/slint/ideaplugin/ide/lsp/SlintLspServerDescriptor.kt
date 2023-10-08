package dev.slint.ideaplugin.ide.lsp

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.ProjectWideLspServerDescriptor
import dev.slint.ideaplugin.lang.SlintFileType
import org.eclipse.lsp4j.services.LanguageServer

class SlintLspServerDescriptor(project: Project) : ProjectWideLspServerDescriptor(project, "Slint") {

    override fun isSupportedFile(file: VirtualFile) = file.fileType == SlintFileType

    override fun createCommandLine(): GeneralCommandLine {
        return GeneralCommandLine("/Users/kizeev/.cargo/bin/slint-lsp")
    }

    override val lsp4jServerClass: Class<out LanguageServer> = SlintLanguageServer::class.java
//    override val lspServerListener: LspServerListener = SlintLspServerListener(project)
}