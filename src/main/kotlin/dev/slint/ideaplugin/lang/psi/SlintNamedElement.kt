package dev.slint.ideaplugin.lang.psi

import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiNamedElement

interface SlintNamedElement : SlintElement, PsiNamedElement, NavigatablePsiElement