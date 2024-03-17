package dev.slint.ideaplugin.ide.settings

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.dsl.builder.*

class SlintSettingsComponent(lspSettings: SlintLspSettings) {
    private var panel: DialogPanel? = null

    init {
        panel = panel {
            group("LSP Settings") {
                lateinit var useExternalLspCheckBox: Cell<JBCheckBox>

                row {
                    useExternalLspCheckBox = checkBox("Use external LSP server")
                        .bindSelected(lspSettings::useExternalLsp)
                }
                indent {
                    row {
                        textFieldWithBrowseButton()
                            .label("LSP path:")
                            .bindText(lspSettings::path)
                            .enabledIf(useExternalLspCheckBox.selected)
                            .align(AlignX.FILL)
                    }
                }
                row("Args:") {
                    expandableTextField()
                        .comment("The command line arguments passed to the Slint LSP server")
                        .bindText(lspSettings::args)
                        .align(AlignX.FILL)
                }
                row {
                    val pathsTablePanel = PathsTablePanel()
                    cell(pathsTablePanel.component)
                        .comment("List of paths in which the `import` statement and `@image-url` are looked up")
                        .align(AlignX.FILL)
                        .label("Include paths:", LabelPosition.TOP)
                        .onIsModified { pathsTablePanel.onModified(lspSettings.includePaths) }
                        .onApply { pathsTablePanel.onApply(lspSettings.includePaths) }
                        .onReset { pathsTablePanel.onReset(lspSettings.includePaths) }
                }
                row {
                    checkBox("Restart LSP server")
                        .comment(
                            "Restarting the LSP server after a crash or shutdown. " +
                                    "If there are critical errors in the LSP server, the server will restart indefinitely." +
                                    "<br>Manually restart the server: 'Tools -> Restart Slint LSP'"
                        )
                        .bindSelected(lspSettings::isRestartLsp)
                }
            }
            group("Preview") {
                row {
                    checkBox("Hide the toolbar of the preview")
                        .comment("")
                        .bindSelected(lspSettings::noToolbar)
                }
                row("Style:") {
                    comboBox(SlintStyle.values().toList())
                        .bindItem(lspSettings::style.toNullableProperty())
                }
                row("Backend:") {
                    comboBox(SlintBackend.values().toList())
                        .bindItem(lspSettings::backend.toNullableProperty())
                }
            }
        }
    }

    fun getPanel(): DialogPanel? {
        return panel
    }
}