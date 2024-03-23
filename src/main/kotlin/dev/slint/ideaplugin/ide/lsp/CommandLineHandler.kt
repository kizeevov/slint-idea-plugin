package dev.slint.ideaplugin.ide.lsp

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.ide.plugins.PluginManager
import com.intellij.openapi.extensions.PluginId
import com.intellij.openapi.util.SystemInfo
import dev.slint.ideaplugin.ide.settings.SlintBackend
import dev.slint.ideaplugin.ide.settings.SlintSettingsState
import dev.slint.ideaplugin.ide.settings.SlintStyle
import java.nio.file.Path
import java.nio.file.attribute.PosixFilePermission
import kotlin.io.path.getPosixFilePermissions
import kotlin.io.path.isExecutable
import kotlin.io.path.setPosixFilePermissions

object CommandLineHandler {
    fun createCommandLine(): GeneralCommandLine {
        val settingState = SlintSettingsState.getInstance().lspSettings

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

        val path = if (settingState.useExternalLsp) {
            settingState.path
        } else {
            getEmbeddedLspPath().toString()
        }

        return GeneralCommandLine(path).apply {
            addParameters(parameters)
            withParentEnvironmentType(GeneralCommandLine.ParentEnvironmentType.CONSOLE)
            withCharset(Charsets.UTF_8)
        }
    }

    private fun getEmbeddedLspPath(): Path? {
        val pluginManager = PluginManager
            .getInstance()
            .findEnabledPlugin(PluginId.getId(dev.slint.ideaplugin.SLINT_PLUGIN_ID))
            ?: return null

        val programName: String

        if (SystemInfo.isMac) {
            programName = "Slint Live Preview.app/Contents/MacOS/slint-lsp"
        } else if (SystemInfo.isLinux) {
            programName = when (SystemInfo.OS_ARCH) {
                "x64" -> {
                    "slint-lsp-x86_64-unknown-linux-gnu"
                }

                "arm" -> {
                    "slint-lsp-armv7-unknown-linux-gnueabihf"
                }

                "arm64" -> {
                    "slint-lsp-aarch64-unknown-linux-gnu"
                }

                else -> {
                    return null
                }
            }
        } else if (SystemInfo.isWindows) {
            programName = "slint-lsp-x86_64-pc-windows-msvc.exe"
        } else {
            return null
        }

        val lspPath = pluginManager
            .pluginPath
            .resolve("language-server/bin")
            .resolve(programName)

        if (!lspPath.isExecutable()) {
            lspPath.setPosixFilePermissions(
                lspPath.getPosixFilePermissions()
                    .plus(PosixFilePermission.OWNER_EXECUTE)
            )
        }

        return lspPath
    }
}
