package dev.slint.ideaplugin.ide.lsp

import com.intellij.execution.process.OSProcessHandler
import com.intellij.execution.process.ProcessAdapter
import com.intellij.execution.process.ProcessEvent
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import dev.slint.ideaplugin.ide.services.SlintServerService
import dev.slint.ideaplugin.ide.settings.SlintSettingsState
import java.util.*
import kotlin.concurrent.schedule

object ServerProcessHandler {
    fun addListeners(handler: OSProcessHandler, project: Project): OSProcessHandler {
        handler.addProcessListener(object : ProcessAdapter() {
            override fun processTerminated(event: ProcessEvent) {
                super.processTerminated(event)

                val slintServerService = project.service<SlintServerService>()
                if (slintServerService.isRestarting) {
                    return
                }

                slintServerService.setTerminatingStatus()

                val settingState = SlintSettingsState.getInstance().lspSettings
                if (!settingState.isRestartLsp) {
                    return
                }

                Timer().schedule(3000) {
                    slintServerService.restartServer()
                }
            }

            override fun processNotStarted() {
                super.processNotStarted()

                val slintServerService = project.service<SlintServerService>()
                slintServerService.setTerminatingStatus()
            }

            override fun startNotified(event: ProcessEvent) {
                super.startNotified(event)

                val slintServerService = project.service<SlintServerService>()
                slintServerService.setRunningStatus()
            }
        })

        return handler
    }
}