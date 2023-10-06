package dev.slint.ideaplugin

import com.intellij.DynamicBundle
import org.jetbrains.annotations.NonNls
import org.jetbrains.annotations.PropertyKey

@NonNls
const val SLINT_BUNDLE = "messages.SlintBundle"

object SlintBundle : DynamicBundle(SLINT_BUNDLE) {

    @Suppress("SpreadOperator")
    @JvmStatic
    fun message(@PropertyKey(resourceBundle = SLINT_BUNDLE) key: String, vararg params: Any) =
            getMessage(key, *params)

    @Suppress("SpreadOperator", "unused")
    @JvmStatic
    fun messagePointer(@PropertyKey(resourceBundle = SLINT_BUNDLE) key: String, vararg params: Any) =
            getLazyMessage(key, *params)
}