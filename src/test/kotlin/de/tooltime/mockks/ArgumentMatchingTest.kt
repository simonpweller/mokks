package de.tooltime.mockks

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

private class ArgumentMatchingMock {
    fun call(value: Int) = value
}

internal class ArgumentMatchingTest {

    @Test
    fun `argument matching works`() {
        val mock = mockk<ArgumentMatchingMock>()

        every { mock.call(any()) } returns 2 // any

        assertEquals(2, mock.call(10))

        every { mock.call(5) } returns 1 // value

        assertEquals(1, mock.call(5))

        every { mock.call(eq(6)) } returns 3 // eq

        assertEquals(3, mock.call(6))

        every { mock.call(or(less(2), more(10))) } returns 4 // or, less, more

        assertEquals(4, mock.call(1))
        assertEquals(4, mock.call(11))
        assertNotEquals(4, mock.call(5))

        every { mock.call(and(neq(1), neq(2))) } returns 7 // and, neq

        assertNotEquals(7, mock.call(1))
        assertNotEquals(7, mock.call(2))
        assertEquals(7, mock.call(0))

        every { mock.call(match { it > 2 }) } returns 8 // match
        assertEquals(8, mock.call(3))
        assertNotEquals(8, mock.call(2))
    }
}
