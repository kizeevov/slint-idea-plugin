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
import com.jetbrains.rd.generator.nova.PredefinedType
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

        val imports = root.allImports()
        var startOffset = 0
        var startImport: SlintImportDefinition? = null

        for (import in imports) {
            if (import.isPrevImport().not()) {
                startOffset = import.startOffset;
                startImport = import
            }
            if (import.isNextImport().not() && startImport != null) {
                descriptors += FoldingDescriptor(startImport, TextRange(startOffset, import.endOffset))
            }
        }
    }

    override fun getLanguagePlaceholderText(node: ASTNode, textRange: TextRange): String = "import ..."

    override fun isRegionCollapsedByDefault(node: ASTNode): Boolean = false

}

private fun PsiElement.allImports() = childrenOfType(SlintImportDefinition::class).toList()

private fun PsiElement.isPrevImport(): Boolean {
    val next = this.prevSibling

    if (next is PsiWhiteSpace) {
        return next.isPrevImport()
    }

    if (next is PsiComment) {
        return next.isPrevImport()
    }

    return next is SlintImportDefinition
}

private fun PsiElement.isNextImport(): Boolean {
    val next = this.nextSibling

    if (next is PsiWhiteSpace) {
        return next.isNextImport()
    }

    if (next is PsiComment) {
        return next.isNextImport()
    }

    return next is SlintImportDefinition
}
