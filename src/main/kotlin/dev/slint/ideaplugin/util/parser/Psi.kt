package dev.slint.ideaplugin.util.parser

import com.intellij.openapi.application.runReadAction
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import kotlin.reflect.KClass

/**
 * @see [PsiTreeUtil.getChildrenOfType]
 */
fun <T : PsiElement> PsiElement.childrenOfType(clazz: KClass<T>): Collection<T> {
    if (project.isDisposed || !this.isValid) return emptyList()
    return runReadAction { PsiTreeUtil.findChildrenOfType(this, clazz.java) }
}

/**
 * Finds the first child of a certain type.
 */
@Suppress("UNCHECKED_CAST")
fun <T : PsiElement> PsiElement.firstChildOfType(clazz: KClass<T>): T? {
    val children = runReadAction { this.children }
    for (child in children) {
        if (clazz.java.isAssignableFrom(child.javaClass)) {
            return child as? T
        }

        val first = child.firstChildOfType(clazz)
        if (first != null) {
            return first
        }
    }

    return null
}