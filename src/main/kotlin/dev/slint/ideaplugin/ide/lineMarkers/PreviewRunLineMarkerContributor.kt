package dev.slint.ideaplugin.ide.lineMarkers

import com.intellij.execution.lineMarker.RunLineMarkerContributor
import com.intellij.psi.PsiElement
import com.intellij.psi.util.elementType
import dev.slint.ideaplugin.ide.actions.PreviewComponentAction
import dev.slint.ideaplugin.lang.psi.SlintElementTypes.*

class PreviewRunLineMarkerContributor : RunLineMarkerContributor() {
    override fun getInfo(element: PsiElement): Info? {
        return when (element.elementType) {
            COMPONENT -> {
                Info(PreviewComponentAction(""))
            }

            else -> {
                null
            }
        }
    }
}
