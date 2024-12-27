package dev.slint.ideaplugin

import com.intellij.testFramework.ParsingTestCase
import dev.slint.ideaplugin.lang.parser.SlintParserDefinition


class SimpleParsingTest : ParsingTestCase("", "slint", SlintParserDefinition()) {
    fun testParsingTestData() {
        doTest(true)
    }

    override fun getTestDataPath(): String {
        return "testData"
    }

    override fun includeRanges(): Boolean {
        return true
    }
}