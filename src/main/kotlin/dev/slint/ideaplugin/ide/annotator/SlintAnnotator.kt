package dev.slint.ideaplugin.ide.annotator

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.psi.PsiElement
import com.intellij.psi.util.elementType
import dev.slint.ideaplugin.ide.highlighting.SlintColors
import dev.slint.ideaplugin.lang.psi.SlintElementTypes

class SlintAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element.elementType == SlintElementTypes.FIELD_IDENTIFIER) {
            annotateWithInfo(element, holder, SlintColors.FIELD_NAME)
        }
        else if (element.elementType == SlintElementTypes.CHANGED_KEYWORD) {
            annotateWithInfo(element, holder, SlintColors.KEYWORD)
        }
        else if (element.elementType == SlintElementTypes.DURATION_LITERAL
            || element.elementType == SlintElementTypes.LENGTH_LITERAL
            || element.elementType == SlintElementTypes.PHYSICAL_LENGTH_LITERAL
            || element.elementType == SlintElementTypes.ANGLE_LITERAL
            || element.elementType == SlintElementTypes.COLOR_LITERAL
            || element.elementType == SlintElementTypes.RELATIVE_FONT_SIZE_LITERAL
        ) {
            annotateWithInfo(element, holder, SlintColors.NUMBER)
        }
    }

    private fun annotateWithInfo(
        element: PsiElement,
        holder: AnnotationHolder,
        attributesKey: TextAttributesKey
    ) {
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
            .range(element.textRange)
            .textAttributes(attributesKey)
            .create()
    }
}