package dev.slint.ideaplugin.lang.psi

import com.intellij.psi.tree.IFileElementType
import dev.slint.ideaplugin.lang.SlintLanguage

object SlintFileElementType : IFileElementType("SLINT_FILE", SlintLanguage)