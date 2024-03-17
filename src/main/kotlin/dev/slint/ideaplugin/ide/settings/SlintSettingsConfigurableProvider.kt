package dev.slint.ideaplugin.ide.settings

import com.intellij.openapi.components.service
import com.intellij.openapi.options.Configurable
import com.intellij.openapi.options.SearchableConfigurable
import com.intellij.openapi.project.Project
import dev.slint.ideaplugin.SlintBundle
import dev.slint.ideaplugin.ide.services.SlintServerService
import javax.swing.JComponent

class SlintSettingsConfigurable(internal val project: Project) : Configurable, SearchableConfigurable {

    private var settingsComponent: SlintSettingsComponent? = null

    override fun getDisplayName(): String = SlintBundle.message("configurable.name.slint.settings")

    override fun getId(): String = "slint.settings"

    override fun createComponent(): JComponent? {
        val settings = SlintSettingsState.getInstance().lspSettings
        settingsComponent = SlintSettingsComponent(settings)
        return settingsComponent?.getPanel()
    }

    override fun isModified(): Boolean {
        return settingsComponent?.getPanel()?.isModified() ?: false
    }

    override fun apply() {
        settingsComponent?.getPanel()?.apply()

        val slintServerService = project.service<SlintServerService>()
        slintServerService.restartServer()
        slintServerService.notifyRestart()
    }

    override fun reset() {
        settingsComponent?.getPanel()?.reset()
    }

    override fun disposeUIResources() {
        settingsComponent = null
    }
}