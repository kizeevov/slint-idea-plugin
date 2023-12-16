package dev.slint.ideaplugin.ide.widgets

import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.FileEditorManagerListener
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.wm.StatusBarWidget
import com.intellij.openapi.wm.StatusBarWidget.WidgetPresentation
import com.intellij.openapi.wm.impl.status.EditorBasedWidget
import com.intellij.platform.lsp.api.LspServerManager
import com.intellij.platform.lsp.impl.LspServerImpl
import dev.slint.ideaplugin.SlintBundle
import dev.slint.ideaplugin.SlintIcons
import dev.slint.ideaplugin.ide.lsp.SlintLspServerSupportProvider
import dev.slint.ideaplugin.lang.SlintFileType
import javax.swing.Icon

class SlintWidget(project: Project) : EditorBasedWidget(project), StatusBarWidget,
    StatusBarWidget.MultipleTextValuesPresentation {
    private val logger: Logger = Logger.getInstance(javaClass)
    private var isShowWidget = false

    init {
        project.messageBus.connect(this).subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, object : FileEditorManagerListener {
            override fun fileOpened(source: FileEditorManager, file: VirtualFile) {
                val isSlintFile = source.openFiles.any {
                    it.fileType == SlintFileType
                }

                if (isShowWidget != isSlintFile) {
                    isShowWidget = isSlintFile
                    update()
                }
            }

            override fun fileClosed(source: FileEditorManager, file: VirtualFile) {
                val isSlintFile = source.openFiles.any {
                    it.fileType == SlintFileType
                }

                if (isShowWidget != isSlintFile) {
                    isShowWidget = isSlintFile
                    update()
                }
            }
        })
    }

    override fun ID(): String {
        return javaClass.name;
    }

    override fun getIcon(): Icon {
        return SlintIcons.SLINT
    }

    override fun getPresentation(): WidgetPresentation {
        return this
    }

    override fun getSelectedValue(): String? {
        return if (isShowWidget) "slint-lsp" else ""
    }

    override fun getTooltipText(): String {
        val lspServerManager = LspServerManager.getInstance(project)
        val lspServer = lspServerManager.getServersForProvider(SlintLspServerSupportProvider::class.java).firstOrNull()

        return when (lspServer) {
            is LspServerImpl -> {
                if (lspServer.isRunning) {
                    SlintBundle.message("slint.language.server.is.running")
                } else {
                    SlintBundle.message("slint.language.server.is.stopped")
                }
            }

            else -> {
                SlintBundle.message("slint.language.server.is.stopped")
            }
        }
    }

    private fun update() {
        if (myStatusBar == null) {
            logger.warn("Failed to update slint statusbar")
            return
        }

        myStatusBar!!.updateWidget(ID())
    }

}