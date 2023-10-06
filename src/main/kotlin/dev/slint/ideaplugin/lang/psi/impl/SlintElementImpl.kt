package dev.slint.ideaplugin.lang.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import dev.slint.ideaplugin.lang.psi.SlintElement

open class SlintElementImpl(node: ASTNode) : ASTWrapperPsiElement(node), SlintElement