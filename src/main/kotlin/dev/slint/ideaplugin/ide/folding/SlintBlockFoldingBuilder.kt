package dev.slint.ideaplugin.ide.folding

import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.DumbAware
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import dev.slint.ideaplugin.lang.psi.*

class SlintBlockFoldingBuilder : FoldingBuilderEx(), DumbAware {
    override fun buildFoldRegions(root: PsiElement, document: Document, quick: Boolean): Array<FoldingDescriptor> {
        val blocks = PsiTreeUtil.findChildrenOfAnyType(
            root,
            SlintComponentBody::class.java,
            SlintInternalComponentBody::class.java,
            SlintCallbackBody::class.java,
            SlintAnimateBody::class.java,
            SlintStatesBody::class.java,
            SlintFunctionBody::class.java,
            SlintStructBody::class.java,
            SlintAnonymousStructBody::class.java,
        )
        return blocks.map { FoldingDescriptor(it, it.textRange) }.toTypedArray()
    }

    override fun getPlaceholderText(node: ASTNode): String = "{...}"

    override fun isCollapsedByDefault(node: ASTNode): Boolean = false
}