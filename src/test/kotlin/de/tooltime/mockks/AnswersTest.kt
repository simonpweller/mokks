package de.tooltime.mockks

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.justRun
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

private class AnswersMock {
    fun call(value: Int) = value
    fun callReturningUnit(value: Int) {}
}

internal class AnswersTest {

    @Test
    fun `answers work`() {
        val mock = mockk<AnswersMock>()

        every { mock.call(5) } returnsMany listOf(1, 2, 3)
        assertEquals(1, mock.call(5))
        assertEquals(2, mock.call(5))
        assertEquals(3, mock.call(5))

        every { mock.call(4) } throws RuntimeException("what happened?")
        assertThrows<RuntimeException> { mock.call(4) }

        every { mock.callReturningUnit(3) } just Runs
        assertEquals(Unit, mock.callReturningUnit(3))
        justRun { mock.callReturningUnit(2) }
        assertEquals(Unit, mock.callReturningUnit(2))

        every { mock.call(1) } answers { arg<Int>(0) + 5 }
        assertEquals(6, mock.call(1))
    }
}
