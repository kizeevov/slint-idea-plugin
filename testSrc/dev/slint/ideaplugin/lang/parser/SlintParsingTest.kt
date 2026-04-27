package dev.slint.ideaplugin.lang.parser

import com.intellij.testFramework.ParsingTestCase

class SlintParsingTest : ParsingTestCase("parser", "slint", SlintParserDefinition()) {

    override fun getTestDataPath(): String = "src/testData"

    override fun skipSpaces(): Boolean = false

    override fun includeRanges(): Boolean = true

    fun testKeysSimple() = doTest(true)
    fun testKeysModifiers() = doTest(true)
    fun testMarkdownBasic() = doTest(true)
    fun testMarkdownConcatenated() = doTest(true)
    fun testStyledText() = doTest(true)
    fun testPostfixChainPropertyInit() = doTest(true)
}
