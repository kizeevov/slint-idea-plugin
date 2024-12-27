package test.kotlin

import com.intellij.testFramework.ParsingTestCase
import dev.slint.ideaplugin.lang.parser.SlintParserDefinition


class SimpleParsingTest : ParsingTestCase("", "slint", SlintParserDefinition()) {
    fun testParsingTestData() {
        doTest(true)
    }

    override fun getTestDataPath(): String {
        return "src/test/testData"
    }

    override fun includeRanges(): Boolean {
        return true
    }
}