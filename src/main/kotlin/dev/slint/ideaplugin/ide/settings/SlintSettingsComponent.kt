package dev.slint.ideaplugin.ide.settings

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.*

class SlintSettingsComponent(settings: SlintLspSettings) {
    private var panel: DialogPanel? = null

    private var lspSettings: SlintLspSettings = SlintState.getInstance().lspSettings

    init {
        panel = panel {
            row("Slint-lsp path") {
                textFieldWithBrowseButton()
                        .bindText(lspSettings::path)
                        .align(AlignX.FILL)
            }
        }
    }

    fun getPanel(): DialogPanel? {
        return panel
    }
}