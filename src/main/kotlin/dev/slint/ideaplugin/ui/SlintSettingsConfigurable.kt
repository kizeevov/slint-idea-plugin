package dev.slint.ideaplugin.ui

import com.intellij.openapi.options.Configurable
import com.intellij.openapi.options.ConfigurableProvider
import com.intellij.openapi.options.SearchableConfigurable
import com.intellij.openapi.ui.MasterDetailsComponent
import dev.slint.ideaplugin.SlintBundle

internal class SlintSettingsConfigurable : SearchableConfigurable, MasterDetailsComponent() {
    override fun getDisplayName(): String = SlintBundle.message("configurable.name.slint.settings")

    override fun getId(): String = "slint.settings"
}

class SlintSettingsConfigurableProvider : ConfigurableProvider() {
    override fun createConfigurable(): Configurable = SlintSettingsConfigurable()
}