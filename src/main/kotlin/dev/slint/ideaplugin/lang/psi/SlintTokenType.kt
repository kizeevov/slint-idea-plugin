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
val SLINT_FIELD_LITERAL = create(PARAMETER_LITERAL, TYPE_LITERAL)

//val PRISMA_BLOCKS = create(FIELD_DECLARATION_BLOCK, KEY_VALUE_BLOCK, ENUM_DECLARATION_BLOCK)
//val PRISMA_DECLARATIONS = create(
//        MODEL_DECLARATION, TYPE_DECLARATION, VIEW_DECLARATION, DATASOURCE_DECLARATION,
//        GENERATOR_DECLARATION, ENUM_DECLARATION, TYPE_ALIAS
//)
//val PRISMA_TOP_ELEMENTS = orSet(PRISMA_DECLARATIONS, PRISMA_COMMENTS)
//val PRISMA_BLOCK_DECLARATIONS = create(FIELD_DECLARATION, BLOCK_ATTRIBUTE, KEY_VALUE, ENUM_VALUE_DECLARATION)
//
//val PRISMA_TYPES = create(
//        FIELD_TYPE,
//        SINGLE_TYPE,
//        UNSUPPORTED_OPTIONAL_LIST_TYPE,
//        LIST_TYPE,
//        LEGACY_LIST_TYPE,
//        OPTIONAL_TYPE,
//        LEGACY_REQUIRED_TYPE
//)

val PRISMA_LITERALS = create(STRING_LITERAL, NUMERIC_LITERAL)