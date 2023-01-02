package de.tooltime.mockks

import io.mockk.MockKException
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class RelaxedMocksTest {

    @Test
    fun `relaxed mocks work without specifying behavior`() {
        val strictMock = mockk<Divider>()

        assertThrows<MockKException> { strictMock.divide(4, 2) }

        val relaxedMock = mockk<Divider>(relaxed = true)

        assertEquals(0, relaxedMock.divide(4, 2))
        verify { relaxedMock.divide(4, 2) }

        val spy = spyk<Divider>()

        assertEquals(2, spy.divide(4, 2))
        verify { spy.divide(4, 2) }
    }
}
