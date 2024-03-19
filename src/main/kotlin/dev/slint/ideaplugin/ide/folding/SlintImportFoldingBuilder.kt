package dev.slint.ideaplugin.ide.folding

import com.intellij.lang.ASTNode
import com.intellij.lang.folding.CustomFoldingBuilder
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiWhiteSpace
import com.intellij.refactoring.suggested.endOffset
import com.intellij.refactoring.suggested.startOffset
import dev.slint.ideaplugin.lang.psi.SlintFile
import dev.slint.ideaplugin.lang.psi.SlintImportDefinition
import dev.slint.ideaplugin.util.parser.childrenOfType

class SlintImportFoldingBuilder : CustomFoldingBuilder(), DumbAware {
    override fun buildLanguageFoldRegions(
        descriptors: MutableList<FoldingDescriptor>,
        root: PsiElement,
        document: Document,
        quick: Boolean
    ) {
        if (root !is SlintFile) {
            return
        }

        val covered = HashSet<SlintImportDefinition>()
        val imports = root.allImports()

        for (import in imports) {
            if (import in covered) {
                continue
            }

            var next: SlintImportDefinition? = import
            var last: SlintImportDefinition = import
            while (next != null) {
                covered += next
                last = next
                next = next.nextImport()
            }

            descriptors += FoldingDescriptor(import, TextRange(import.startOffset, last.endOffset))
        }
    }

    override fun getLanguagePlaceholderText(node: ASTNode, textRange: TextRange): String = "import ..."

    override fun isRegionCollapsedByDefault(node: ASTNode): Boolean = false

}

private fun PsiElement.allImports() = childrenOfType(SlintImportDefinition::class).toList()

private fun PsiElement.nextImport(): SlintImportDefinition? {
    val next = this.nextSibling

    if (next is PsiWhiteSpace) {
        return next.nextImport()
    }

    if (next is PsiComment) {
        return next.nextImport()
    }

    return next as? SlintImportDefinition
}