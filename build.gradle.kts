import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.intellij.tasks.PatchPluginXmlTask
import org.jetbrains.changelog.Changelog
import org.jetbrains.changelog.markdownToHTML
import de.undercouch.gradle.tasks.download.Download

fun properties(key: String) = providers.gradleProperty(key)
fun environment(key: String) = providers.environmentVariable(key)

plugins {
    id("java")
    alias(libs.plugins.kotlin)
    alias(libs.plugins.intellij)
    alias(libs.plugins.grammarkit)
    alias(libs.plugins.changelog)
    alias(libs.plugins.download)
}

group = properties("pluginGroup").get()
version = properties("pluginVersion").get()

val slintLspVersion: String = properties("slintLspVersion").get()

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
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    pluginName = properties("pluginName")
    version = properties("platformVersion")
    type = properties("platformType")
    plugins = properties("platformPlugins").map { it.split(',').map(String::trim).filter(String::isNotEmpty) }
}

changelog {
    groups.empty()
    repositoryUrl = properties("pluginRepositoryUrl")
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
        // classpath(project(":$grammarKitFakePsiDeps").sourceSets.main.get().runtimeClasspath)
    }

    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
        dependsOn(generateLexer, generateParser)
    }
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
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
        changeNotes = properties("pluginVersion").map { pluginVersion ->
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
        channels = properties("pluginVersion").map { listOf(it.split('-').getOrElse(1) { "default" }.split('.').first()) }
    }

//    task("downloadSlintLspVscodePlugin", type = Download::class) {
//        src("https://Slint.gallery.vsassets.io/_apis/public/gallery/publisher/Slint/extension/slint/${slintLspVersion}/assetbyname/Microsoft.VisualStudio.Services.VSIXPackage")
//        dest("${project.buildDir}/tmp/slint-${slintLspVersion}-vscode-plugin.zip")
//        onlyIfModified(true)
//    }
//
//    task("extractSlintLspVscodePlugin", type = Copy::class) {
//        dependsOn("downloadSlintLspVscodePlugin")
//        from(zipTree("${project.buildDir}/tmp/slint-${slintLspVersion}-vscode-plugin.zip")) {
//            destinationDir = file("${project.buildDir}/tmp/slint-vscode-plugin")
//        }
//    }

    prepareSandbox {
        // dependsOn("extractSlintLspVscodePlugin")
        from("${project.buildDir}/tmp/slint-vscode-plugin/extension/bin") {
            into("${intellij.pluginName.get()}/language-server/bin")
        }
    }
}