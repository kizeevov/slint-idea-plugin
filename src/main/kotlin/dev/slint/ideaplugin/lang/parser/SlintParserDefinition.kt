package dev.slint.ideaplugin.lang.parser

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import dev.slint.ideaplugin.lang.psi.SlintElementTypes
import dev.slint.ideaplugin.lang.lexer.SlintLexer
import dev.slint.ideaplugin.lang.psi.*

class SlintParserDefinition : ParserDefinition {
    override fun createLexer(project: Project?): Lexer = SlintLexer()

    override fun createParser(project: Project?): PsiParser = SlintParser()

    override fun getFileNodeType(): IFileElementType = SlintFileElementType

    override fun getCommentTokens(): TokenSet = SLINT_COMMENTS

    override fun getStringLiteralElements(): TokenSet = SLINT_STRINGS
    override fun createElement(node: ASTNode?): PsiElement = SlintElementTypes.Factory.createElement(node)

    override fun createFile(viewProvider: FileViewProvider): PsiFile = SlintFile(viewProvider)
}