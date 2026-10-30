package dev.slint.ideaplugin.ide.lsp

import com.intellij.platform.lsp.api.customization.LspCompletionCustomizer
import com.intellij.platform.lsp.api.customization.LspCustomization
import com.intellij.platform.lsp.api.customization.LspDocumentColorCustomizer
import com.intellij.platform.lsp.api.customization.LspDocumentColorDisabled
import com.intellij.platform.lsp.api.customization.LspDocumentColorSupport
import com.intellij.platform.lsp.api.customization.LspFormattingCustomizer
import dev.slint.ideaplugin.ide.settings.SlintSettingsState

class SlintLspCustomization : LspCustomization() {
    override val completionCustomizer: LspCompletionCustomizer = SlintLspCompletionSupport()
    override val formattingCustomizer: LspFormattingCustomizer = SlintLspFormattingSupport()
    override val documentColorCustomizer: LspDocumentColorCustomizer =
        if (SlintSettingsState.getInstance().lspSettings.colorPicker) LspDocumentColorDisabled
        else LspDocumentColorSupport()
}
