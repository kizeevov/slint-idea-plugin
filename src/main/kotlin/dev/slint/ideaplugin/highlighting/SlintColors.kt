package dev.slint.ideaplugin.highlighting

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey

object SlintColors {
    val DOC_COMMENT = createTextAttributesKey("SLINT_DOC_COMMENT", DefaultLanguageHighlighterColors.DOC_COMMENT)
    val LINE_COMMENT = createTextAttributesKey("SLINT_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
    val STRING_LITERAL = createTextAttributesKey("SLINT_STRING_LITERAL", DefaultLanguageHighlighterColors.STRING)
    val KEYWORD = createTextAttributesKey("SLINT_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)
    val IDENTIFIER = createTextAttributesKey("SLINT_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER)
    val NUMBER = createTextAttributesKey("SLINT_NUMBER", DefaultLanguageHighlighterColors.NUMBER)
    val BRACKETS = createTextAttributesKey("SLINT_BRACKETS", DefaultLanguageHighlighterColors.BRACKETS)
    val PARENTHESES = createTextAttributesKey("SLINT_PARENTHESES", DefaultLanguageHighlighterColors.PARENTHESES)
    val BRACES = createTextAttributesKey("SLINT_BRACES", DefaultLanguageHighlighterColors.BRACES)
    val DOT = createTextAttributesKey("SLINT_DOT", DefaultLanguageHighlighterColors.DOT)
    val COMMA = createTextAttributesKey("SLINT_COMMA", DefaultLanguageHighlighterColors.COMMA)
    val OPERATION_SIGN =
            createTextAttributesKey("SLINT_OPERATION_SIGN", DefaultLanguageHighlighterColors.OPERATION_SIGN)

    val TYPE_NAME = createTextAttributesKey("SLINT_TYPE_NAME", DefaultLanguageHighlighterColors.CLASS_NAME)
    val TYPE_REFERENCE = createTextAttributesKey("SLINT_TYPE_REFERENCE", TYPE_NAME)
    val ATTRIBUTE = createTextAttributesKey("SLINT_ATTRIBUTE", DefaultLanguageHighlighterColors.METADATA)
    val PARAMETER = createTextAttributesKey("SLINT_PARAMETER", DefaultLanguageHighlighterColors.PARAMETER)
    val FIELD_NAME = createTextAttributesKey("SLINT_FIELD_NAME", DefaultLanguageHighlighterColors.INSTANCE_FIELD)
    val FIELD_REFERENCE = createTextAttributesKey("SLINT_FIELD_REFERENCE", FIELD_NAME)
    val FUNCTION = createTextAttributesKey("SLINT_FUNCTION", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION)
}