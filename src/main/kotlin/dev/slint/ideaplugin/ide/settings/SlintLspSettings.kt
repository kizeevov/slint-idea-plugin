package dev.slint.ideaplugin.ide.settings

import java.util.*

data class SlintLspSettings(
    var path: String = "slint-lsp",
    var args: String = "",
    var style: SlintStyle = SlintStyle.DEFAULT,
    var backend: SlintBackend = SlintBackend.DEFAULT,
    var noToolbar: Boolean = false,
    var includePaths: MutableList<String> = mutableListOf(),
    var useExternalLsp: Boolean = false,
    var providedByEditor: Boolean = false
)

enum class SlintStyle {
    DEFAULT,
    FLUENT,
    MATERIAL,
    NATIVE;

    override fun toString(): String {
        return name.lowercase(Locale.getDefault())
    }
}

enum class SlintBackend {
    DEFAULT,
    QT,
    FEMTOVG,
    SKIA,
    SOFTWARE;

    override fun toString(): String {
        return name.lowercase(Locale.getDefault())
    }
}