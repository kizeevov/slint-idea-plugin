package dev.slint.ideaplugin.ide.settings

import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.ui.MessageDialogBuilder
import com.intellij.ui.ToolbarDecorator
import com.intellij.ui.table.TableView
import com.intellij.util.ui.ColumnInfo
import javax.swing.JComponent
import com.intellij.util.ui.ListTableModel
import dev.slint.ideaplugin.SlintBundle
import javax.swing.table.DefaultTableCellRenderer
import javax.swing.table.TableCellRenderer

internal class PathsTablePanel {
    val component: JComponent

    private val model: ListTableModel<String> = ListTableModel(PathColumnInfo())
    private val table: TableView<String> = TableView(model).apply {
        visibleRowCount = 5
        rowSelectionAllowed = false
        tableHeader.resizingAllowed = true
        tableHeader.setUI(null)
    }

    init {
        val toolbarTable = ToolbarDecorator.createDecorator(table)
            .setAddAction { addData() }
            .setRemoveAction { removeData() }
            .disableUpDownActions()
            .createPanel()

        component = toolbarTable
    }

    fun onModified(paths: MutableList<String>): Boolean {
        return model.items != paths
    }

    fun onApply(paths: MutableList<String>) {
        paths.clear()
        model.items.forEach {
            paths.add(it)
        }
    }

    fun onReset(paths: MutableList<String>) {
        repeat(model.items.size) { model.removeRow(0) }
        model.addRows(paths)
    }

    private fun addData() {
        val fileChooserDescriptor = FileChooserDescriptorFactory.createSingleFolderDescriptor()
        FileChooser.chooseFile(fileChooserDescriptor, null, null) {
            model.addRow(it.path)
        }
    }

    private fun removeData() {
        val dialog = MessageDialogBuilder.okCancel(
            SlintBundle.message("settings.paths.table.remove.dialog.title"),
            SlintBundle.message("settings.paths.table.remove.dialog.message")
        )

        if (dialog.guessWindowAndAsk()) {
            model.removeRow(table.selectedRow)
        }
    }
}

internal class PathColumnInfo : ColumnInfo<String, String>(null) {

    private val renderer = DefaultTableCellRenderer()

    override fun valueOf(item: String): String = item

    override fun getRenderer(item: String?): TableCellRenderer = renderer

    override fun getComparator(): java.util.Comparator<String> = COMPARATOR

    companion object {
        private val COMPARATOR = Comparator<String> { path1, path2 ->
            path1.compareTo(path2, true)
        }
    }
}