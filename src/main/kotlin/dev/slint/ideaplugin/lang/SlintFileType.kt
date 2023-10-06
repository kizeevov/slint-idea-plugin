package dev.slint.ideaplugin.lang

import com.intellij.openapi.fileTypes.LanguageFileType
import dev.slint.ideaplugin.SlintBundle
import dev.slint.ideaplugin.SlintIcons
import javax.swing.Icon

object SlintFileType : LanguageFileType(SlintLanguage) {
    override fun getName(): String = "Slint"

    override fun getDescription(): String = SlintBundle.message("filetype.slint.description")

    override fun getDefaultExtension(): String = "slint"

    override fun getIcon(): Icon = SlintIcons.SLINT
}