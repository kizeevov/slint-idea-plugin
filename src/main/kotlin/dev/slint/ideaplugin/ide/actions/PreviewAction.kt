package dev.slint.ideaplugin.ide.actions

import com.intellij.icons.AllIcons
import com.intellij.notification.Notification
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.CommonDataKeys.VIRTUAL_FILE
import com.intellij.platform.lsp.api.LspServer
import dev.slint.ideaplugin.ide.lsp.SlintLspServer
import dev.slint.ideaplugin.ide.lsp.requests.PreviewMessageRequest
import dev.slint.ideaplugin.lang.SlintLanguage
import kotlin.io.path.Path

internal class PreviewAction(private val notification: Notification? = null) :
        LspAction("Show All Preview", null, AllIcons.Actions.Preview)
{
    override fun update(e: AnActionEvent) {
        val psiFile = e.getData(CommonDataKeys.PSI_FILE) ?: return
        e.presentation.isEnabledAndVisible = psiFile.language.isKindOf(SlintLanguage.INSTANCE)
    }

    override fun actionPerformed(e: AnActionEvent, servers: List<SlintLspServer>) {
        val virtualFile = e.getData(VIRTUAL_FILE) ?: return
        val uriFile = Path(virtualFile.path).toUri()

        val request = PreviewMessageRequest(servers.first(), uriFile.toString(), "")
        (servers.first() as LspServer).requestExecutor.sendRequestSync(request)

        notification?.expire()
    }
}