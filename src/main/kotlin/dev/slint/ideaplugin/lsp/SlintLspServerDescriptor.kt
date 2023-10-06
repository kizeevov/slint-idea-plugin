package dev.slint.ideaplugin.lsp

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.ProjectWideLspServerDescriptor
import dev.slint.ideaplugin.lang.SlintFileType
import org.eclipse.lsp4j.services.LanguageServer

class SlintLspServerDescriptor(project: Project) : ProjectWideLspServerDescriptor(project, "Slint") {

    override fun isSupportedFile(file: VirtualFile) = file.fileType == SlintFileType

    override fun createCommandLine(): GeneralCommandLine {
//        val interpreter = NodeJsInterpreterManager.getInstance(project).interpreter
//        if (interpreter !is NodeJsLocalInterpreter && interpreter !is WslNodeInterpreter) {
//            // shouldn't happen, checked in PrismaLspServerSupportProvider
//            throw ExecutionException(PrismaBundle.message("prisma.interpreter.not.configured"))
//        }
//
//        val lsp = JSLanguageServiceUtil.getPluginDirectory(javaClass, "language-server/prisma-language-server.js")
//        if (lsp == null || !lsp.exists()) {
//            // broken plugin installation?
//            throw ExecutionException(PrismaBundle.message("prisma.language.server.not.found"))
//        }
//
//        return GeneralCommandLine().apply {
//            withParentEnvironmentType(GeneralCommandLine.ParentEnvironmentType.CONSOLE)
//            withCharset(Charsets.UTF_8)
//            addParameter(lsp.path)
//            addParameter("--stdio")
//
//            NodeCommandLineConfigurator.find(interpreter)
//                    .configure(this, NodeCommandLineConfigurator.defaultOptions(project))
//        }
        return GeneralCommandLine("/Users/kizeev/.cargo/bin/slint-lsp")
    }

//    override val lspGoToDefinitionSupport = false
//    override val lspCompletionSupport = null

    override val lsp4jServerClass: Class<out LanguageServer> = SlintLanguageServer::class.java
//    override val lspServerListener: LspServerListener = SlintLspServerListener(project)
}