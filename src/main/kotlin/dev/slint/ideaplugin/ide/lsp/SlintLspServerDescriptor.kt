package dev.slint.ideaplugin.ide.lsp

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.process.OSProcessHandler
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.Lsp4jClient
import com.intellij.platform.lsp.api.LspServerListener
import com.intellij.platform.lsp.api.LspServerNotificationsHandler
import com.intellij.platform.lsp.api.ProjectWideLspServerDescriptor
import com.intellij.platform.lsp.api.customization.LspCompletionSupport
import dev.slint.ideaplugin.ide.settings.SlintSettingsState
import dev.slint.ideaplugin.lang.SlintFileType
import org.eclipse.lsp4j.services.LanguageServer

@Suppress("UnstableApiUsage")
class SlintLspServerDescriptor(project: Project) : ProjectWideLspServerDescriptor(project, "Slint") {

    override fun isSupportedFile(file: VirtualFile) = file.fileType == SlintFileType

    override fun createCommandLine(): GeneralCommandLine = CommandLineHandler.createCommandLine()

    override fun createInitializationOptions(): Any = SlintSettingsState.getInstance().lspSettings

    // Suppression of the verifier plugin warning
    // override fun createLsp4jClient(handler: LspServerNotificationsHandler): Lsp4jClient = LspLanguageClient(handler)

    override fun startServerProcess(): OSProcessHandler =
        ServerProcessHandler.addListeners(super.startServerProcess(), project)

    override val lsp4jServerClass: Class<out LanguageServer> = SlintLanguageServer::class.java
    override val lspServerListener: LspServerListener = SlintLspServerListener(project)
    override val lspCompletionSupport: LspCompletionSupport = SlintLspCompletionSupport()
}