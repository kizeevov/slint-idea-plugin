package dev.slint.ideaplugin.ide.highlighting

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import dev.slint.ideaplugin.lang.psi.SlintElementTypes
import dev.slint.ideaplugin.lang.lexer.SlintLexer
import dev.slint.ideaplugin.lang.psi.*

class SlintSyntaxHighlighter : SyntaxHighlighterBase() {
    override fun getHighlightingLexer(): Lexer = SlintLexer()

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> =
            pack(attributes[tokenType])

    companion object {
        private val attributes = buildMap<IElementType, TextAttributesKey> {
            put(SlintElementTypes.DOC_COMMENT, SlintColors.DOC_COMMENT)
            put(SlintElementTypes.LINE_COMMENT, SlintColors.LINE_COMMENT)
            put(SlintElementTypes.STRING_LITERAL, SlintColors.STRING_LITERAL)

            fillMap(this, SLINT_KEYWORDS, SlintColors.KEYWORD)
            fillMap(this, SLINT_KEYWORDS_OTHER, SlintColors.KEYWORD)
            fillMap(this, SLINT_KEYWORDS_CONTROL, SlintColors.KEYWORD)

            fillMap(this, SLINT_BRACKETS, SlintColors.BRACKETS)
            fillMap(this, SLINT_BRACES, SlintColors.BRACES)
            fillMap(this, SLINT_PARENTHESES, SlintColors.PARENTHESES)

            fillMap(this, SLINT_NUMERIC_LITERAL, SlintColors.NUMBER)


//            put(SlintParserDefinition.DOC_COMMENT, SlintColors.DOC_COMMENT)
//            put(SlintParserDefinition.LINE_COMMENT, SlintColors.LINE_COMMENT)
//            put(SlintElementTypes.IDENTIFIER, SlintColors.IDENTIFIER)
//            put(PrismaElementTypes.NUMERIC_LITERAL, SlintColors.NUMBER)
//            put(PrismaElementTypes.COMMA, SlintColors.COMMA)
//            put(PrismaElementTypes.DOT, SlintColors.DOT)
//            put(PrismaElementTypes.EQ, SlintColors.OPERATION_SIGN)
//            put(PrismaElementTypes.COLON, SlintColors.OPERATION_SIGN)
//            put(PrismaElementTypes.QUEST, SlintColors.OPERATION_SIGN)
//            put(PrismaElementTypes.EXCL, SlintColors.OPERATION_SIGN)
//            put(PrismaElementTypes.UNSUPPORTED, SlintColors.TYPE_REFERENCE)

//            fillMap(this, PRISMA_PARENTHESES, SlintColors.PARENTHESES)
        }
    }
}