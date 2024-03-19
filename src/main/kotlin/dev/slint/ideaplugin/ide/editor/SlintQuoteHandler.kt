package dev.slint.ideaplugin.ide.editor

import com.intellij.codeInsight.editorActions.SimpleTokenSetQuoteHandler
import dev.slint.ideaplugin.lang.psi.SLINT_STRINGS

class SlintQuoteHandler : SimpleTokenSetQuoteHandler(SLINT_STRINGS)