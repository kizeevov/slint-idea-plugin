package dev.slint.ideaplugin.ide.lsp

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.*
import com.intellij.platform.lsp.api.customization.LspCompletionSupport
import com.intellij.platform.lsp.api.customization.LspFormattingSupport
import dev.slint.ideaplugin.ide.settings.SlintSettingsState
import dev.slint.ideaplugin.lang.SlintFileType
import org.eclipse.lsp4j.services.LanguageServer

@Suppress("UnstableApiUsage")
class SlintLspServerDescriptor(project: Project) : ProjectWideLspServerDescriptor(project, "Slint") {

    override fun isSupportedFile(file: VirtualFile) = file.fileType == SlintFileType

    override fun createCommandLine(): GeneralCommandLine = CommandLineHandler.createCommandLine()

    override fun createInitializationOptions(): Any = SlintSettingsState.getInstance().lspSettings

    override fun createLsp4jClient(handler: LspServerNotificationsHandler): Lsp4jClient = SlintLspClient(handler)

    override val lsp4jServerClass: Class<out LanguageServer> = SlintLspServer::class.java
    override val lspServerListener: LspServerListener = SlintLspServerListener(project)
    override val lspCompletionSupport: LspCompletionSupport = SlintLspCompletionSupport()
    override val lspFormattingSupport: LspFormattingSupport = SlintLspFormattingSupport()
}