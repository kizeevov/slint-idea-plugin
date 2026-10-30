package dev.slint.ideaplugin.ide.color

import com.intellij.openapi.command.CommandProcessor
import com.intellij.openapi.editor.ElementColorProvider
import com.intellij.openapi.editor.RangeMarker
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Key
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiElement
import com.intellij.psi.util.elementType
import dev.slint.ideaplugin.ide.settings.SlintSettingsState
import dev.slint.ideaplugin.lang.psi.SlintElementTypes
import java.awt.Color

class SlintColorProvider : ElementColorProvider {
    override fun getColorFrom(element: PsiElement): Color? {
        if (element.elementType != SlintElementTypes.COLOR_LITERAL) return null
        if (!SlintSettingsState.getInstance().lspSettings.colorPicker) return null
        return parseColor(element.text)
    }

    override fun setColorTo(element: PsiElement, color: Color) {
        val target = element.getUserData(COLOR_TARGET) ?: createTarget(element)?.also { element.putUserData(COLOR_TARGET, it) } ?: return
        val marker = target.marker
        if (!marker.isValid) return

        val text = formatColor(color, target.uppercase)
        CommandProcessor.getInstance().executeCommand(target.project, {
            marker.document.replaceString(marker.startOffset, marker.endOffset, text)
            PsiDocumentManager.getInstance(target.project).commitDocument(marker.document)
        }, "Change Color", target, marker.document)
    }

    private fun createTarget(element: PsiElement): ColorTarget? {
        val project = element.project
        val document = PsiDocumentManager.getInstance(project).getDocument(element.containingFile) ?: return null
        val marker = document.createRangeMarker(element.textRange).apply {
            isGreedyToLeft = true
            isGreedyToRight = true
        }
        return ColorTarget(project, marker, element.text.any { it.isUpperCase() })
    }

    private class ColorTarget(val project: Project, val marker: RangeMarker, val uppercase: Boolean)

    companion object {
        private val COLOR_TARGET = Key.create<ColorTarget>("dev.slint.ideaplugin.colorTarget")

        fun parseColor(text: String): Color? {
            val hex = text.removePrefix("#")
            if (hex.length !in setOf(3, 4, 6, 8) || hex.any { Character.digit(it, 16) == -1 }) return null

            val channels = if (hex.length <= 4) {
                hex.map { Character.digit(it, 16) * 17 }
            } else {
                hex.chunked(2).map { it.toInt(16) }
            }
            return Color(channels[0], channels[1], channels[2], channels.getOrElse(3) { 255 })
        }

        fun formatColor(color: Color, uppercase: Boolean = false): String {
            val channels = mutableListOf(color.red, color.green, color.blue)
            if (color.alpha != 255) channels.add(color.alpha)

            val hex = channels.joinToString("") { "%02x".format(it) }
            return "#" + if (uppercase) hex.uppercase() else hex
        }
    }
}
