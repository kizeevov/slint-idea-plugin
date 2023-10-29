package dev.slint.ideaplugin.lang

import com.intellij.json.JsonLanguage
import com.intellij.lang.Language

object SlintLanguage : Language("Slint") {
    val INSTANCE = SlintLanguage

    override fun getDisplayName(): String = "Slint"
}