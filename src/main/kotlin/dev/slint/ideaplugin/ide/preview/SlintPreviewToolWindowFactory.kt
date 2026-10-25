package dev.slint.ideaplugin.ide.preview

import com.intellij.openapi.components.service
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory

class SlintPreviewToolWindowFactory : ToolWindowFactory, DumbAware {
    override fun shouldBeAvailable(project: Project): Boolean = project.service<SlintPreviewService>().isEnabled

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        project.service<SlintPreviewService>().ensureContent(toolWindow)
    }
}
