package dev.slint.ideaplugin.ide.lineMarkers

import com.intellij.execution.lineMarker.RunLineMarkerContributor
import com.intellij.psi.PsiElement
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.psi.PsiWhiteSpace
import com.intellij.psi.TokenType.WHITE_SPACE
import com.intellij.psi.util.elementType
import com.intellij.psi.util.nextLeafs
import dev.slint.ideaplugin.ide.actions.PreviewComponentAction
import dev.slint.ideaplugin.lang.psi.SlintElementTypes.COMPONENT
import dev.slint.ideaplugin.lang.psi.SlintElementTypes.IDENTIFIER
import dev.slint.ideaplugin.lang.psi.SlintFileElementType

class PreviewRunLineMarkerContributor : RunLineMarkerContributor() {
    override fun getInfo(element: PsiElement): Info? {
        return when (element.elementType) {
            SlintFileElementType -> {
                Info(ActionManager.getInstance().getAction("Slint.Preview"))
            }
            COMPONENT -> {
                val componentName = getComponentName(element) ?: return null
                Info(PreviewComponentAction(componentName))
            }
            else -> {
                null
            }
        }
    }

    private fun getComponentName(element: PsiElement): String? {
        val psiWhiteSpace = element.nextSibling
        if (psiWhiteSpace.elementType != WHITE_SPACE) {
            return null
        }

        val component = psiWhiteSpace.nextSibling
        if (component.elementType != IDENTIFIER) {
            return null
        }

        return component.text
    }
}
