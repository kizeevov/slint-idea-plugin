package dev.slint.ideaplugin.ide.lsp

import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.customization.LspFormattingSupport
import dev.slint.ideaplugin.lang.SlintFileType

@Suppress("UnstableApiUsage")
class SlintLspFormattingSupport : LspFormattingSupport() {
    override fun shouldFormatThisFileExclusivelyByServer(
        file: VirtualFile,
        ideCanFormatThisFileItself: Boolean,
        serverExplicitlyWantsToFormatThisFile: Boolean
    ) = file.fileType == SlintFileType
}