package dev.slint.ideaplugin.ide.preview

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.intellij.ide.util.PropertiesComponent
import com.intellij.notification.NotificationAction
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.Disposable
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowManager
import com.intellij.ui.components.JBPanelWithEmptyText
import com.intellij.ui.content.ContentFactory
import com.intellij.ui.jcef.JBCefApp
import dev.slint.ideaplugin.SlintBundle
import dev.slint.ideaplugin.ide.services.SlintServerService
import dev.slint.ideaplugin.ide.settings.SlintSettingsState
import javax.swing.JComponent

internal const val SLINT_PREVIEW_TOOL_WINDOW_ID = "Slint Preview"
internal const val SLINT_NOTIFICATION_GROUP_ID = "Slint"

@Service(Service.Level.PROJECT)
class SlintPreviewService(private val project: Project) {
    @Volatile
    private var panel: SlintPreviewPanel? = null

    val isEnabled: Boolean
        get() = SlintSettingsState.getInstance().lspSettings.providedByEditor && isJcefSupported()

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
        val toolWindow = getToolWindow() ?: return
        if (isEnabled) {
            toolWindow.isAvailable = true
            ensureContent(toolWindow)
            return
        }

        toolWindow.contentManager.removeAllContents(true)
        toolWindow.isAvailable = false
    }

    fun suggestPreviewInsideIde(file: VirtualFile, component: String) {
        if (isEnabled || !isJcefSupported()) return

        val properties = PropertiesComponent.getInstance()
        if (properties.getBoolean(PREVIEW_INSIDE_IDE_SUGGESTED_KEY)) return
        properties.setValue(PREVIEW_INSIDE_IDE_SUGGESTED_KEY, true)

        NotificationGroupManager.getInstance()
            .getNotificationGroup(SLINT_NOTIFICATION_GROUP_ID)
            .createNotification(
                SlintBundle.message("preview.inside.ide.notification.title"),
                SlintBundle.message("preview.inside.ide.notification.content"),
                NotificationType.INFORMATION
            )
            .addAction(NotificationAction.createSimpleExpiring(SlintBundle.message("preview.inside.ide.notification.enable")) {
                SlintSettingsState.getInstance().lspSettings.providedByEditor = true
                onSettingsChanged()
                project.service<SlintServerService>().previewComponent(file, component)
            })
            .notify(project)
    }

    internal fun ensureContent(toolWindow: ToolWindow) {
        if (toolWindow.contentManager.contentCount > 0) return

        val placeholder = JBPanelWithEmptyText().withEmptyText(SlintBundle.message("preview.toolwindow.empty"))
        placeholder.emptyText.appendLine(SlintBundle.message("preview.toolwindow.empty.hint"))
        addContent(toolWindow, placeholder, null)
    }

    private fun ensurePreview(toolWindow: ToolWindow) {
        if (panel != null) return

        val previewPanel = SlintPreviewPanel(project)
        panel = previewPanel
        Disposer.register(previewPanel, Disposable {
            if (panel === previewPanel) panel = null
        })

        toolWindow.contentManager.removeAllContents(true)
        addContent(toolWindow, previewPanel.component, previewPanel)
    }

    private fun addContent(toolWindow: ToolWindow, component: JComponent, disposer: Disposable?) {
        val content = ContentFactory.getInstance().createContent(component, null, false)
        content.isCloseable = false
        disposer?.let { content.setDisposer(it) }
        toolWindow.contentManager.addContent(content)
    }

    private fun showToolWindow() {
        val toolWindow = getToolWindow() ?: return
        toolWindow.isAvailable = true
        toolWindow.show { ensurePreview(toolWindow) }
    }

    private fun isJcefSupported(): Boolean = try {
        JBCefApp.isSupported()
    } catch (e: NoClassDefFoundError) {
        false
    }

    private fun getToolWindow(): ToolWindow? =
        ToolWindowManager.getInstance(project).getToolWindow(SLINT_PREVIEW_TOOL_WINDOW_ID)

    private companion object {
        const val PREVIEW_INSIDE_IDE_SUGGESTED_KEY = "dev.slint.ideaplugin.previewInsideIdeSuggested"
    }
}
