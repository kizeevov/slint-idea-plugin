package dev.slint.ideaplugin.ide.color

import com.intellij.openapi.application.WriteAction
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import java.awt.Color

class SlintColorProviderTest : BasePlatformTestCase() {
    private val provider = SlintColorProvider()

    fun testParseColor() {
        assertEquals(Color(0x11, 0x22, 0x33), SlintColorProvider.parseColor("#123"))
        assertEquals(Color(0x11, 0x22, 0x33, 0x44), SlintColorProvider.parseColor("#1234"))
        assertEquals(Color(0xab, 0xcd, 0xef), SlintColorProvider.parseColor("#ABCDEF"))
        assertEquals(Color(0xab, 0xcd, 0xef, 0x80), SlintColorProvider.parseColor("#abcdef80"))
        assertNull(SlintColorProvider.parseColor("#12345"))
    }

    fun testFormatColor() {
        assertEquals("#abcdef", SlintColorProvider.formatColor(Color(0xab, 0xcd, 0xef)))
        assertEquals("#ABCDEF80", SlintColorProvider.formatColor(Color(0xab, 0xcd, 0xef, 0x80), uppercase = true))
    }

    fun testSetColor() {
        myFixture.configureByText("test.slint", "component A { background: #f<caret>00; }")
        val element = myFixture.file.findElementAt(myFixture.caretOffset)!!
        assertEquals(Color.RED, provider.getColorFrom(element))

        WriteAction.run<Throwable> { provider.setColorTo(element, Color(0, 128, 255, 64)) }
        myFixture.checkResult("component A { background: #0080ff40; }")
    }

    fun testSetColorTwiceWithOpenPicker() {
        myFixture.configureByText("test.slint", "component A { background: #F<caret>00; }")
        val element = myFixture.file.findElementAt(myFixture.caretOffset)!!

        WriteAction.run<Throwable> { provider.setColorTo(element, Color(0, 128, 255)) }
        WriteAction.run<Throwable> { provider.setColorTo(element, Color(0, 255, 0, 64)) }
        myFixture.checkResult("component A { background: #00FF0040; }")
    }
}
