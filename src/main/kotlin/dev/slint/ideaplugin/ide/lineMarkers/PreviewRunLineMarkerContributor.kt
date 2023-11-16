package dev.slint.ideaplugin.ide.lineMarkers

import com.intellij.execution.lineMarker.RunLineMarkerContributor
import com.intellij.psi.PsiElement
import com.intellij.psi.util.elementType
import dev.slint.ideaplugin.ide.actions.PreviewComponentAction
import dev.slint.ideaplugin.lang.psi.SlintElementTypes.*

class PreviewRunLineMarkerContributor : RunLineMarkerContributor() {
    override fun getInfo(element: PsiElement): Info? {
        return when (element.elementType) {
            COMPONENT_DEFINITION -> {
                val componentName = getComponentName(element) ?: return null
                Info(PreviewComponentAction(componentName))
            }
            else -> {
                null
            }
        }
    }

    private fun getComponentName(element: PsiElement): String? {
        val componentName = element.children.firstOrNull() ?: return null
        if (componentName.elementType != COMPONENT_NAME) {
            return null
        }
        return componentName.text
    }
}
