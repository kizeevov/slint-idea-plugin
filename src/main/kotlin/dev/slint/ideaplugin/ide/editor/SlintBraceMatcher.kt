package dev.slint.ideaplugin.ide.editor

import com.intellij.lang.BracePair
import com.intellij.lang.PairedBraceMatcher
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType
import dev.slint.ideaplugin.lang.psi.SlintElementTypes

class SlintBraceMatcher: PairedBraceMatcher {
    override fun getPairs(): Array<BracePair> = braces

    override fun isPairedBracesAllowedBeforeType(lbraceType: IElementType, contextType: IElementType?): Boolean = true

    override fun getCodeConstructStart(file: PsiFile, openingBraceOffset: Int): Int = openingBraceOffset

    companion object {
        private val braces = arrayOf(
            BracePair(SlintElementTypes.LBRACE, SlintElementTypes.RBRACE, true),
            BracePair(SlintElementTypes.LBRACKET, SlintElementTypes.RBRACKET, false),
            BracePair(SlintElementTypes.LPAREN, SlintElementTypes.RPAREN, false),
        )
    }
}