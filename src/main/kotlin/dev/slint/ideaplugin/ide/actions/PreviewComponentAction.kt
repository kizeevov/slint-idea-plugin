package dev.slint.ideaplugin.ide.actions

import com.intellij.icons.AllIcons
import com.intellij.notification.Notification
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.platform.lsp.api.LspServer
import dev.slint.ideaplugin.ide.lsp.SlintLspServer
import dev.slint.ideaplugin.ide.lsp.requests.PreviewMessageRequest
import kotlin.io.path.Path

internal class PreviewComponentAction(
        private val componentName: String,
        private val notification: Notification? = null,
) :
        LspAction("Show Component Preview", null, AllIcons.Actions.ShowCode)
{
    override fun actionPerformed(e: AnActionEvent, servers: List<SlintLspServer>) {
        val virtualFile = e.getData(CommonDataKeys.VIRTUAL_FILE) ?: return
        val uriFile = Path(virtualFile.path).toUri()

        val request = PreviewMessageRequest(servers.first(), uriFile.toString(), componentName)
        (servers.first() as LspServer).requestExecutor.sendRequestSync(request)

        notification?.expire()
    }
}