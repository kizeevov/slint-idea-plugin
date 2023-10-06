package dev.slint.ideaplugin.lang.psi

import com.intellij.psi.tree.IElementType
import dev.slint.ideaplugin.lang.SlintLanguage

class SlintElementType(debugName: String) : IElementType(debugName, SlintLanguage)