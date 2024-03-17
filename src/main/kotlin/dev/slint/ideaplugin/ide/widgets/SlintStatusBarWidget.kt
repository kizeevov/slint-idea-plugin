package dev.slint.ideaplugin.ide.widgets

import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.openapi.components.service
import com.intellij.openapi.module.ModuleUtil
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectLocator
import com.intellij.openapi.ui.popup.JBPopupFactory
import com.intellij.openapi.ui.popup.ListPopup
import com.intellij.openapi.util.IconLoader.getDisabledIcon
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.wm.CustomStatusBarWidget
import com.intellij.openapi.wm.StatusBarWidget
import com.intellij.openapi.wm.impl.status.EditorBasedStatusBarPopup
import com.intellij.ui.AnimatedIcon
import com.intellij.util.concurrency.EdtExecutorService
import dev.slint.ideaplugin.SlintBundle
import dev.slint.ideaplugin.SlintIcons
import dev.slint.ideaplugin.ide.actions.RestartLspAction
import dev.slint.ideaplugin.ide.services.SlintServerService
import dev.slint.ideaplugin.lang.SlintFileType
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

class SlintStatusBarWidget(project: Project) : EditorBasedStatusBarPopup(project, false), CustomStatusBarWidget {
    private companion object {
        const val ID = "Slint.StatusBarWidget"
    }

    private var future: ScheduledFuture<*>? = null

    init {
        this.future = EdtExecutorService
            .getScheduledExecutorInstance()
            .scheduleWithFixedDelay({ this.update() }, 2, 2, TimeUnit.SECONDS)
    }

    override fun ID(): String = ID

    override fun createInstance(project: Project): StatusBarWidget = SlintStatusBarWidget(project)

    override fun createPopup(context: DataContext): ListPopup? {
        val group = DefaultActionGroup.createPopupGroupWithEmptyText()
        group.add(ActionManager.getInstance().getAction(RestartLspAction.ID))

        return JBPopupFactory.getInstance()
            .createActionGroupPopup("Slint Actions", group, context, JBPopupFactory.ActionSelectionAid.SPEEDSEARCH, true)
    }

    override fun getWidgetState(file: VirtualFile?): WidgetState {
        if (file?.fileType !is SlintFileType) return WidgetState.HIDDEN

        val project = ProjectLocator.getInstance().guessProjectForFile(file)
        project?: return WidgetState.HIDDEN
        val module = ModuleUtil.findModuleForFile(file, this.project)
        module?: return WidgetState.HIDDEN

        val slintServerService = project.service<SlintServerService>()

        return when(slintServerService.isRunning) {
            true -> {
                val state = WidgetState(SlintBundle.message("slint.language.server.is.running"), "Slint", false)
                state.icon = SlintIcons.SLINT
                state
            }
            false -> {
                val state = WidgetState(SlintBundle.message("slint.language.server.is.stopped"), "Slint", true)
                state.icon = AnimatedIcon(1000,  SlintIcons.SLINT, getDisabledIcon( SlintIcons.SLINT))
                state
            }
        }
    }

    override fun dispose() {
        this.future?.cancel(true)
        this.future = null

        super<CustomStatusBarWidget>.dispose()
    }
}