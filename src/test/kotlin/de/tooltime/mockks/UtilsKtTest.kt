package de.tooltime.mockks

import io.mockk.every
import io.mockk.mockkStatic
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class UtilsKtTest {

    @Test
    fun `parseAndMap throws`() {
        fun sanitize(string: String) = lc(string).trim()

        mockkStatic("de.tooltime.mockks.UtilsKt")
        every { lc(any()) } throws IllegalArgumentException("nope")

        assertThrows<java.lang.IllegalArgumentException> { sanitize("some string") }
    }
}
