package dev.slint.ideaplugin.ide.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

@Service(Service.Level.PROJECT)
@State(name = "SlintState", storages = [Storage("slint.xml")])
class SlintState : PersistentStateComponent<SlintStateData> {
    var lspSettings: SlintLspSettings = SlintLspSettings()

    companion object {
        fun getInstance(): SlintState {
            return ApplicationManager.getApplication().getService(SlintState::class.java)
        }
    }

    override fun getState(): SlintStateData {
        return SlintStateData(this.lspSettings)
    }

    override fun loadState(state: SlintStateData) {
        this.lspSettings = state.appSettings
    }


}