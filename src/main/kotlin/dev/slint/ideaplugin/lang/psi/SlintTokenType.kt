package dev.slint.ideaplugin.lang.psi

import com.intellij.psi.TokenType.WHITE_SPACE
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.TokenSet.create
import dev.slint.ideaplugin.lang.SlintLanguage
import dev.slint.ideaplugin.lang.psi.SlintElementTypes.*

class SlintTokenType(debugName: String) : IElementType(debugName, SlintLanguage)

val SLINT_STRINGS = create(STRING_LITERAL)
val SLINT_COMMENTS = create(DOC_COMMENT, LINE_COMMENT)
val SLINT_WS = create(WHITE_SPACE)
val SLINT_KEYWORDS = create(IMPORT, FROM, EXPORT, STRUCT, ENUM, GLOBAL, COMPONENT, INHERITS)
val SLINT_KEYWORDS_OTHER = create(
        PROPERTY,
        CALLBACK,
        CHANGED,
        ANIMATE,
        STATES,
        TRANSITIONS,
        PRIVATE,
        PUBLIC,
        PURE,
        FUNCTION,
        IN,
        OUT,
        IN_OUNT
)
val SLINT_KEYWORDS_CONTROL = create(IF, FOR, RETURN)
val SLINT_BRACES = create(LBRACE, RBRACE)
val SLINT_BRACKETS = create(LBRACKET, RBRACKET)
val SLINT_PARENTHESES = create(LPAREN, RPAREN)
val SLINT_NUMERIC_LITERAL = create(NUMERIC_LITERAL)