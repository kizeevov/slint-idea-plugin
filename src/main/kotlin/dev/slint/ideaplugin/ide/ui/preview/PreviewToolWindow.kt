package dev.slint.ideaplugin.ide.ui.preview

import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogPanel
import com.intellij.openapi.ui.SimpleToolWindowPanel
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory
import com.intellij.ui.dsl.builder.AlignX
import com.intellij.ui.dsl.builder.AlignY
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.dsl.builder.plus
import com.intellij.ui.jcef.JBCefBrowser

class PreviewToolWindowFactory : ToolWindowFactory, DumbAware {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val toolwindowPanel = PreviewToolWindowPanel(project)
        val tab = ContentFactory.getInstance()
            .createContent(toolwindowPanel, "", false)
        toolWindow.contentManager.addContent(tab)
    }

}

private class PreviewToolWindowPanel(project: Project) : SimpleToolWindowPanel(true, false) {
    private val previewPanel = PreviewToolWindow(project)

    init {
        setContent(previewPanel.panel)
    }
}

class PreviewToolWindow (private val project: Project) {
    private var preview = SlintPreview()

    var panel: DialogPanel = panel {
        row {
            cell(preview.component)
                .align(AlignY.FILL + AlignX.FILL)
                .resizableColumn()
        }
            .resizableRow()
    }
}