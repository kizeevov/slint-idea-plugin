import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun properties(key: String) = project.findProperty(key).toString()

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.9.0"
    id("org.jetbrains.intellij") version "1.16.0"
    id("org.jetbrains.grammarkit") version "2022.3.1"
}

group = properties("pluginGroup")
version = properties("pluginVersion")

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

//    jvmToolchain {
//        languageVersion.set(JavaLanguageVersion.of(properties("jvmVersion")))
//    }
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
    pluginName.set(properties("pluginName"))
    version.set(properties("platformVersion"))
    type.set(properties("platformType"))
    plugins.set(properties("platformPlugins").split(',').map(String::trim).filter(String::isNotEmpty))
}

tasks {
    generateLexer {
        sourceFile.set(file("src/main/grammars/SlintLexer.flex"))
        targetDir.set("src/gen/dev/slint/ideaplugin/lang/lexer")
        targetClass.set("_SlintLexer")
        purgeOldFiles.set(true)
    }
    generateParser {
        sourceFile.set(file("src/main/grammars/SlintParser.bnf"))
        targetRoot.set("src/gen")
        pathToParser.set("dev/slint/ideaplugin/lang/parser/SlintParser.java")
        pathToPsiRoot.set("dev/slint/ideaplugin/lang/psi")
        purgeOldFiles.set(true)
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

    patchPluginXml {
        sinceBuild.set("222")
        untilBuild.set("232.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
