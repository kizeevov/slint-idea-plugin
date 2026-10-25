package dev.slint.ideaplugin.ide.lsp

import com.intellij.openapi.application.PathManager
import java.nio.file.Path

internal object SlintPluginPaths {
    private val languageServerDir: Path
        get() = PathManager
            .getPluginsDir()
            .resolve("slint-idea-plugin")
            .resolve("language-server")

    val binDir: Path
        get() = languageServerDir.resolve("bin")

    val wasmDir: Path
        get() = languageServerDir.resolve("wasm")
}
