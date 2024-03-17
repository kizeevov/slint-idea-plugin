package dev.slint.ideaplugin.ide.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

@Service(Service.Level.PROJECT)
@State(
    name = "SlintState",
    storages = [Storage("slint.xml")]
)
class SlintSettingsState : PersistentStateComponent<SlintSettingsState> {
    var lspSettings: SlintLspSettings = SlintLspSettings()

    companion object {
        fun getInstance(): SlintSettingsState {
            return ApplicationManager.getApplication().getService(SlintSettingsState::class.java)
        }
    }

    override fun getState(): SlintSettingsState {
        return this
    }

    override fun loadState(state: SlintSettingsState) {
        XmlSerializerUtil.copyBean(state, this);
    }
}