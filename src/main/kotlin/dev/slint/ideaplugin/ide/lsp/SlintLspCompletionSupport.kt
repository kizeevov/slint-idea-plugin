package dev.slint.ideaplugin.ide.lsp

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.editor.EditorModificationUtil
import com.intellij.platform.lsp.api.customization.LspCompletionSupport
import dev.slint.ideaplugin.SlintIcons
import org.eclipse.lsp4j.CompletionItem
import org.eclipse.lsp4j.CompletionItemKind
import javax.swing.Icon

@Suppress("UnstableApiUsage")
class SlintLspCompletionSupport : LspCompletionSupport() {
    override fun getIcon(item: CompletionItem): Icon {
        return super.getIcon(item) ?: SlintIcons.SLINT
    }

    override fun createLookupElement(parameters: CompletionParameters, item: CompletionItem): LookupElement? {
        val lookupElement = super.createLookupElement(parameters, item) as LookupElementBuilder

        if (item.kind == CompletionItemKind.Property) {
            return lookupElement.withInsertHandler { context, _ ->
                EditorModificationUtil.insertStringAtCaret(context.editor, ": ;", false, 2)
            }
        }

        return lookupElement
    }
}