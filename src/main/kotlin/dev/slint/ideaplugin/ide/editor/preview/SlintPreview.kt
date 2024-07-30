package dev.slint.ideaplugin.ide.editor.preview

import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.event.DocumentEvent
import com.intellij.openapi.editor.event.DocumentListener
import com.intellij.openapi.fileEditor.FileEditor
import com.intellij.openapi.fileEditor.FileEditorState
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.util.UserDataHolderBase
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.ui.jcef.*
import com.intellij.util.EventDispatcher
import java.awt.BorderLayout
import java.beans.PropertyChangeListener
import java.util.*
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.Timer


internal const val SLINT_EDITOR_NAME = "SlintEditorPreview"
internal const val SLINT_WASM_INDEX_URL = "http://localhost/index.html"

internal class SlintPreview(val project: Project, private val file: VirtualFile, private val document: Document) :
    UserDataHolderBase(), FileEditor,
    DumbAware {

    private val dispatcher = EventDispatcher.create(PropertyChangeListener::class.java)
    private val component: JComponent = JPanel(BorderLayout())
    private val cefClient = JBCefApp.getInstance().createClient()
    private val browser: JBCefBrowser = JBCefBrowserBuilder().setClient(cefClient).build()

    private val textChangesTimer: Timer

    private val internalUuid = UUID.randomUUID().toString()
    private val internalUrl = "user://$internalUuid.slint.rs/${file.name}"

    init {
        component.add(browser.component, BorderLayout.CENTER)

        cefClient.addRequestHandler(SlintWasmRequestHandler(this), browser.cefBrowser)
        cefClient.addDisplayHandler(ConsoleMessageHandler(), browser.cefBrowser)
        cefClient.addLoadHandler(
            SlintLoadHandler(
                {
                    onShowUi()
                },
                browser
            ),
            browser.cefBrowser
        )

        browser.loadURL(SLINT_WASM_INDEX_URL)
        textChangesTimer = createTextChangesTimer()
        addDocumentListener()
    }

    override fun dispose() {
        browser.cefBrowser.client.removeRequestHandler()
        Disposer.dispose(browser)
    }

    override fun getComponent(): JComponent = component

    override fun getPreferredFocusedComponent(): JComponent = browser.cefBrowser.uiComponent as JComponent

    override fun getName(): String = SLINT_EDITOR_NAME

    override fun setState(state: FileEditorState) {}

    override fun isModified(): Boolean = false

    override fun isValid(): Boolean = true

    override fun addPropertyChangeListener(listener: PropertyChangeListener) {
        dispatcher.addListener(listener)
    }

    override fun removePropertyChangeListener(listener: PropertyChangeListener) {
        dispatcher.removeListener(listener)
    }

    private fun preview() {
        val content = document.text
        val setContentJs = """
            window.dispatchEvent(new CustomEvent("slint.lspToPreview", {
                detail: {
                    message: {
                        SetContents: {
                            url: {
                                url: `$internalUrl`,
                            },
                            contents: `$content`
                        }
                    }
                }
            }));
            
            window.dispatchEvent(new CustomEvent("slint.lspToPreview", {
                detail: {
                    message: {
                        ShowPreview: {
                            url: "$internalUrl",
                            component: "MainWindow",
                            style: ""
                        }
                    }
                }
            }));
        """.trimIndent()
        browser.cefBrowser.executeJavaScript(setContentJs, browser.cefBrowser.url, 0)
    }

    private fun setContent() {
        val content = document.text
        val setContentJs = """
            window.dispatchEvent(new CustomEvent("slint.lspToPreview", {
                detail: {
                    message: {
                        SetContents: {
                            url: {
                                url: `$internalUrl`,
                            },
                            contents: `$content`
                        }
                    }
                }
            }));
        """.trimIndent()
        browser.cefBrowser.executeJavaScript(setContentJs, browser.cefBrowser.url, 0)
    }

    private fun showPreview() {
        val showPreviewJs = """
            window.dispatchEvent(new CustomEvent("slint.lspToPreview", {
                detail: {
                    message: {
                        ShowPreview: {
                            url: "$internalUrl",
                            component: "MainWindow",
                            style: ""
                        }
                    }
                }
            }));
        """.trimIndent()
        browser.cefBrowser.executeJavaScript(showPreviewJs, browser.cefBrowser.url, 0)
    }

    private fun onShowUi() {
        preview()
    }

    private fun createTextChangesTimer(): Timer {
        return Timer(600) {
            setContent()
        }.apply {
            isRepeats = false
        }
    }

    private fun addDocumentListener() {
        document.addDocumentListener(object : DocumentListener {
            override fun documentChanged(event: DocumentEvent) {
                textChangesTimer.restart()
            }

            override fun bulkUpdateFinished(document: Document) {
                preview()
            }
        })
    }
}
