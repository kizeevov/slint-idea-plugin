import org.jetbrains.changelog.Changelog
import org.jetbrains.changelog.markdownToHTML
import de.undercouch.gradle.tasks.download.Download
import org.jetbrains.intellij.platform.gradle.tasks.PatchPluginXmlTask
import org.jetbrains.intellij.platform.gradle.tasks.VerifyPluginTask
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

fun properties(key: String) = project.findProperty(key).toString()
fun environment(key: String) = providers.environmentVariable(key)

plugins {
    id("java")
    alias(libs.plugins.kotlin)
    alias(libs.plugins.platform)
    alias(libs.plugins.grammarkit)
    alias(libs.plugins.changelog)
    alias(libs.plugins.download)
}

group = properties("pluginGroup")
version = properties("pluginVersion")

val jvmVersion = properties("jvmVersion")
val slintLspVersion: String = properties("slintLspVersion")

idea {
    module {
        generatedSourceDirs.add(file("src/gen"))
    }
}

kotlin {
    sourceSets {
        main {
            kotlin.srcDir("src")
            resources.srcDir("resources")
        }
        test {
            kotlin.srcDir("testSrc")
            resources.srcDir("src/testData")
        }
    }
}

java {
    sourceSets {
        main {
            java.srcDir("src/gen")
            resources.srcDir("resources")
        }
    }
}

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

changelog {
    groups.empty()
    repositoryUrl = properties("pluginRepositoryUrl")
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.3")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    intellijPlatform {
        intellijIdea(properties("platformVersion"))
        bundledPlugin("com.intellij.java")
        plugins(providers.gradleProperty("platformPlugins").map { it.split(',') })
    }
}

intellijPlatform {
    pluginConfiguration {
        name = properties("pluginName")
        version = project.version.toString()

        ideaVersion {
            sinceBuild = properties("pluginSinceBuild")
            untilBuild = properties("pluginUntilBuild")
        }
    }

    pluginVerification {
        failureLevel.set(
            listOf(
                VerifyPluginTask.FailureLevel.COMPATIBILITY_PROBLEMS,
                VerifyPluginTask.FailureLevel.INVALID_PLUGIN,
                VerifyPluginTask.FailureLevel.INTERNAL_API_USAGES,
            )
        )
    }
}

tasks {
    generateLexer {
        sourceFile = file("src/main/grammars/SlintLexer.flex")
        targetOutputDir = file("src/gen/dev/slint/ideaplugin/lang/lexer")
        purgeOldFiles = true
    }
    generateParser {
        sourceFile = file("src/main/grammars/SlintParser.bnf")
        targetRootOutputDir = file("src/gen")
        pathToParser = "dev/slint/ideaplugin/lang/parser/SlintParser.java"
        pathToPsiRoot = "dev/slint/ideaplugin/lang/psi"
        purgeOldFiles = true
    }

    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = jvmVersion
        targetCompatibility = jvmVersion
        dependsOn(generateLexer, generateParser)
    }
    withType<KotlinCompile> {
        compilerOptions.jvmTarget.set(JvmTarget.fromTarget(jvmVersion))
        compilerOptions.languageVersion.set(KotlinVersion.KOTLIN_2_3)
        dependsOn(generateLexer, generateParser)
    }

    withType<PatchPluginXmlTask> {
        version = properties("pluginVersion")
        sinceBuild = properties("pluginSinceBuild")
        untilBuild = properties("pluginUntilBuild")

        // Extract the <!-- Plugin description --> section from README.md and provide for the plugin's manifest
        pluginDescription = providers.fileContents(layout.projectDirectory.file("README.md")).asText.map {
            val start = "<!-- Plugin description -->"
            val end = "<!-- Plugin description end -->"

            with (it.lines()) {
                if (!containsAll(listOf(start, end))) {
                    throw GradleException("Plugin description section not found in README.md:\n$start ... $end")
                }
                subList(indexOf(start) + 1, indexOf(end)).joinToString("\n").let(::markdownToHTML)
            }
        }

        val changelog = project.changelog // local variable for configuration cache compatibility
        // Get the latest available change notes from the changelog file
        changeNotes = providers.gradleProperty("pluginVersion").map { pluginVersion ->
            with(changelog) {
                renderItem(
                    (getOrNull(pluginVersion) ?: getUnreleased())
                        .withHeader(false)
                        .withEmptySections(false),
                    Changelog.OutputType.HTML,
                )
            }
        }
    }

    signPlugin {
        certificateChain = environment("CERTIFICATE_CHAIN")
        privateKey = environment("PRIVATE_KEY")
        password = environment("PRIVATE_KEY_PASSWORD")
    }

    publishPlugin {
        dependsOn("patchChangelog")
        token = environment("PUBLISH_TOKEN")
        // The pluginVersion is based on the SemVer (https://semver.org) and supports pre-release labels, like 2.1.7-alpha.3
        // Specify pre-release label to publish the plugin in a custom Release Channel automatically. Read more:
        // https://plugins.jetbrains.com/docs/intellij/deployment.html#specifying-a-release-channel
        // channels = properties("pluginVersion").map { listOf(it.split('-').getOrElse(1) { "default" }.split('.').first()) }
    }

    register<Download>("downloadSlintLspVscodePlugin") {
        description = "Downloads the Slint LSP binary from the remote repository"
        src("https://Slint.gallery.vsassets.io/_apis/public/gallery/publisher/Slint/extension/slint/${slintLspVersion}/assetbyname/Microsoft.VisualStudio.Services.VSIXPackage")
        dest("${layout.buildDirectory.asFile.get()}/tmp/slint-${slintLspVersion}-vscode-plugin.zip")
        onlyIfModified(true)
        overwrite(false)
    }

    register<Copy>("extractSlintLspVscodePlugin") {
        description = ""
        dependsOn("downloadSlintLspVscodePlugin")
        from(zipTree("${layout.buildDirectory.asFile.get()}/tmp/slint-${slintLspVersion}-vscode-plugin.zip")) {
            destinationDir = file("${layout.buildDirectory.asFile.get()}/tmp/slint-vscode-plugin")
        }
    }

    prepareSandbox {
        dependsOn("extractSlintLspVscodePlugin")
        from("${layout.buildDirectory.asFile.get()}/tmp/slint-vscode-plugin/extension/bin") {
            into("${pluginName.get()}/language-server/bin")
        }
        from("${layout.buildDirectory.asFile.get()}/tmp/slint-vscode-plugin/extension/out") {
            include("slint_lsp_wasm*")
            into("${pluginName.get()}/language-server/wasm")
        }
        from("${project.projectDir}/src/main/resources/wasmPreview/index.html") {
            into("${pluginName.get()}/language-server/wasm")
        }
    }
}