package dev.slint.ideaplugin.ide.preview

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.intellij.openapi.Disposable
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowManager
import com.intellij.ui.content.ContentFactory
import com.intellij.ui.jcef.JBCefApp
import dev.slint.ideaplugin.ide.services.SlintServerService
import dev.slint.ideaplugin.ide.settings.SlintSettingsState

internal const val SLINT_PREVIEW_TOOL_WINDOW_ID = "Slint Preview"

@Service(Service.Level.PROJECT)
class SlintPreviewService(private val project: Project) {
    @Volatile
    private var panel: SlintPreviewPanel? = null

    val isEnabled: Boolean
        get() = SlintSettingsState.getInstance().lspSettings.providedByEditor && JBCefApp.isSupported()

    fun onLspToPreview(message: JsonElement) {
        if (message is JsonObject && message.has("ShowPreview")) {
            ApplicationManager.getApplication().invokeLater({ showToolWindow() }, project.disposed)
        }

        panel?.takeIf { it.isReady }?.postToPreview(message)
    }

    fun onPreviewReady() {
        sendToLsp(JsonObject().apply { add("RequestState", JsonObject()) })
    }

    fun sendToLsp(message: JsonElement) {
        project.service<SlintServerService>().sendPreviewMessage(message)
    }

    fun updatePreviewTarget() {
        val target = if (isEnabled) "embedded-wasm" else "child-process"
        val message = JsonObject().apply {
            add("PreviewTypeChanged", JsonObject().apply { addProperty("target", target) })
        }
        sendToLsp(message)
    }

    fun onSettingsChanged() {
        if (isEnabled) return

        val toolWindow = ToolWindowManager.getInstance(project).getToolWindow(SLINT_PREVIEW_TOOL_WINDOW_ID) ?: return
        toolWindow.contentManager.removeAllContents(true)
        toolWindow.isAvailable = false
    }

    internal fun ensureContent(toolWindow: ToolWindow) {
        if (toolWindow.contentManager.contentCount > 0) return

        val previewPanel = SlintPreviewPanel(project)
        val content = ContentFactory.getInstance().createContent(previewPanel.component, null, false)
        content.isCloseable = false
        content.setDisposer(previewPanel)

        panel = previewPanel
        Disposer.register(previewPanel, Disposable {
            if (panel === previewPanel) panel = null
        })
        toolWindow.contentManager.addContent(content)
    }

    private fun showToolWindow() {
        val toolWindow = ToolWindowManager.getInstance(project).getToolWindow(SLINT_PREVIEW_TOOL_WINDOW_ID) ?: return
        toolWindow.isAvailable = true
        toolWindow.show { ensureContent(toolWindow) }
    }
}
