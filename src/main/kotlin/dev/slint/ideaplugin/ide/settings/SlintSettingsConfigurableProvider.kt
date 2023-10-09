package dev.slint.ideaplugin.ide.settings

import com.intellij.openapi.options.Configurable
import com.intellij.openapi.options.ConfigurableProvider
import com.intellij.openapi.options.SearchableConfigurable
import dev.slint.ideaplugin.SlintBundle
import javax.swing.JComponent

class SlintSettingsConfigurable : Configurable, SearchableConfigurable {

    private var settingsComponent: SlintSettingsComponent? = null

    override fun getDisplayName(): String = SlintBundle.message("configurable.name.slint.settings")

    override fun getId(): String = "slint.settings"

    override fun createComponent(): JComponent? {
        val settings = SlintState.getInstance().lspSettings
        settingsComponent = SlintSettingsComponent(settings)
        return settingsComponent?.getPanel()
    }

    override fun isModified(): Boolean {
        return settingsComponent?.getPanel()?.isModified() ?: false
    }

    override fun apply() {
        settingsComponent?.getPanel()?.apply()
    }

    override fun reset() {
        settingsComponent?.getPanel()?.reset()
    }

    override fun disposeUIResources() {
        settingsComponent = null
    }
}

class SlintSettingsConfigurableProvider : ConfigurableProvider() {
    override fun createConfigurable(): Configurable = SlintSettingsConfigurable()
}