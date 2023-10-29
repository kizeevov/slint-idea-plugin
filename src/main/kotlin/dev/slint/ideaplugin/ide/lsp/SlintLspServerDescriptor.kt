package dev.slint.ideaplugin.ide.lsp

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.Lsp4jClient
import com.intellij.platform.lsp.api.LspServerListener
import com.intellij.platform.lsp.api.LspServerNotificationsHandler
import com.intellij.platform.lsp.api.ProjectWideLspServerDescriptor
import dev.slint.ideaplugin.ide.settings.SlintState
import dev.slint.ideaplugin.lang.SlintFileType
import org.eclipse.lsp4j.services.LanguageServer

class SlintLspServerDescriptor(project: Project) : ProjectWideLspServerDescriptor(project, "Slint") {

    override fun isSupportedFile(file: VirtualFile) = file.fileType == SlintFileType

    override fun createCommandLine(): GeneralCommandLine {
        val settingState = SlintState.getInstance().lspSettings
        return GeneralCommandLine(settingState.path).apply {
            withParentEnvironmentType(GeneralCommandLine.ParentEnvironmentType.CONSOLE)
            withCharset(Charsets.UTF_8)
        }
    }

    override fun createInitializationOptions(): Any = SlintState.getInstance().lspSettings

    override fun createLsp4jClient(handler: LspServerNotificationsHandler): Lsp4jClient = LspLanguageClient(handler)

    override val lsp4jServerClass: Class<out LanguageServer> = SlintLanguageServer::class.java
    override val lspServerListener: LspServerListener = SlintLspServerListener(project)
}