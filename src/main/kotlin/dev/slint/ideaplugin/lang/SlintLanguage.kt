package dev.slint.ideaplugin.lang

import com.intellij.lang.Language

object SlintLanguage : Language("Slint") {
    private fun readResolve(): Any = SlintLanguage

    val INSTANCE = SlintLanguage

    override fun getDisplayName(): String = "Slint"
}