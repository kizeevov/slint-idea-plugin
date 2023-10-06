package dev.slint.ideaplugin.lang

import com.intellij.lang.Language

object SlintLanguage : Language("Slint") {
    override fun getDisplayName(): String = "Slint"
}