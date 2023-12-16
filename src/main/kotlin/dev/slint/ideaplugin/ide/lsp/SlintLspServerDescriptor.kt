package dev.slint.ideaplugin.ide.lsp

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.Lsp4jClient
import com.intellij.platform.lsp.api.LspServerListener
import com.intellij.platform.lsp.api.LspServerNotificationsHandler
import com.intellij.platform.lsp.api.ProjectWideLspServerDescriptor
import com.intellij.platform.lsp.api.customization.LspCompletionSupport
import dev.slint.ideaplugin.ide.settings.SlintBackend
import dev.slint.ideaplugin.ide.settings.SlintState
import dev.slint.ideaplugin.ide.settings.SlintStyle
import dev.slint.ideaplugin.lang.SlintFileType
import org.eclipse.lsp4j.services.LanguageServer

@Suppress("UnstableApiUsage")
class SlintLspServerDescriptor(project: Project) : ProjectWideLspServerDescriptor(project, "Slint") {

    override fun isSupportedFile(file: VirtualFile) = file.fileType == SlintFileType

    override fun createCommandLine(): GeneralCommandLine {
        val settingState = SlintState.getInstance().lspSettings

        val parameters = mutableListOf<String>()
        if (settingState.args.isNotEmpty()) {
            val args = settingState.args.split("\\s+".toRegex())
            parameters.addAll(args)
        }

        if (settingState.includePaths.isNotEmpty()) {
            parameters.add("-I")
            settingState.includePaths.forEach {
                parameters.add("'${it}'")
            }
        }

        if (settingState.backend != SlintBackend.DEFAULT) {
            parameters.add("--backend")
            parameters.add(settingState.backend.toString())
        }

        if (settingState.style != SlintStyle.DEFAULT) {
            parameters.add("--style")
            parameters.add(settingState.style.toString())
        }

        if (settingState.noToolbar) {
            parameters.add("--no-toolbar")
        }

        return GeneralCommandLine(settingState.path).apply {
            addParameters(parameters)
            withParentEnvironmentType(GeneralCommandLine.ParentEnvironmentType.CONSOLE)
            withCharset(Charsets.UTF_8)
        }
    }

    override fun createInitializationOptions(): Any = SlintState.getInstance().lspSettings

    override fun createLsp4jClient(handler: LspServerNotificationsHandler): Lsp4jClient = LspLanguageClient(handler, project)

    override val lsp4jServerClass: Class<out LanguageServer> = SlintLanguageServer::class.java
    override val lspServerListener: LspServerListener = SlintLspServerListener(project)
    override val lspCompletionSupport: LspCompletionSupport = SlintLspCompletionSupport()
}