package de.tooltime.mockks

import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CaptureTest {

    @Test
    fun `capturing works`() {
        val slot = slot<Int>()
        val mock = mockk<Divider>()

        every { mock.divide(capture(slot), any()) } returns 22

        mock.divide(5, 2) // argument 5 is captured to slot

        assertEquals(5, slot.captured)

        every {
            mock.divide(capture(slot), any())
        } answers {
            slot.captured * 11
        }

        assertEquals(110, mock.divide(10, 2))

        // use mutableList instead of slot to capture multiple values
        val list = mutableListOf<Int>()
        every { mock.divide(capture(list), any()) } returns 22
        mock.divide(2, 1)
        mock.divide(4, 1)

        assertEquals(listOf(2, 4), list)
    }
}
