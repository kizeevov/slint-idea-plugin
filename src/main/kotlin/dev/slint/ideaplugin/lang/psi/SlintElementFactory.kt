//package dev.slint.ideaplugin.lang.psi
//
//import com.intellij.openapi.project.Project
//import com.intellij.psi.PsiElement
//import com.intellij.psi.PsiFileFactory
//import com.intellij.psi.util.PsiTreeUtil
//import dev.slint.ideaplugin.lang.SlintLanguage
//import org.jetbrains.annotations.NonNls
//
//object SlintElementFactory {
//    fun createFile(project: Project, @NonNls text: String): SlintFile =
//            PsiFileFactory.getInstance(project).createFileFromText(
//                    "dummy.Slint", SlintLanguage, text, false, true
//            ) as SlintFile
//
//    private inline fun <reified T : SlintElement> createElement(project: Project, text: String): T? =
//            PsiTreeUtil.findChildOfType(createFile(project, text), T::class.java, true)
//
//    fun createIdentifier(project: Project, name: String): PsiElement =
//            createElement<SlintModelDeclaration>(project, "model $name {}")?.nameIdentifier
//                    ?: error("Invalid identifier: $name")
//}