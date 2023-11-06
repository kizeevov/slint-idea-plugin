package dev.slint.ideaplugin

import com.intellij.openapi.util.IconLoader
import javax.swing.Icon

object SlintIcons {

    val SLINT = load("/icons/slint.svg")

    private fun load(path: String): Icon = IconLoader.getIcon(path, SlintIcons::class.java)
}