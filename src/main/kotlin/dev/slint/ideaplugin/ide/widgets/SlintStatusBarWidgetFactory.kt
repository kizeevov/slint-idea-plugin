package dev.slint.ideaplugin.ide.widgets

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.StatusBarWidget
import com.intellij.openapi.wm.StatusBarWidgetFactory

class SlintStatusBarWidgetFactory : StatusBarWidgetFactory {
    override fun getId(): String {
        return "SlintWidget"
    }

    override fun getDisplayName(): String {
        return "Slint"
    }

    override fun createWidget(project: Project): StatusBarWidget {
        return SlintStatusBarWidget(project)
    }
}