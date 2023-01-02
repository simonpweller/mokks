package de.tooltime.mockks

import io.mockk.Called
import io.mockk.clearMocks
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyAll
import io.mockk.verifyOrder
import io.mockk.verifySequence
import org.junit.jupiter.api.Test

private class VerificationMock {
    fun call(value: Int) = value
}

internal class VerificationTest {

    @Test
    fun `verification works`() {
        val mock = mockk<VerificationMock>()

        every { mock.call(any()) } returns 2
        mock.call(5)
        verify { mock.call(5) }
        clearMocks(mock)

        every { mock.call(any()) } returns 2
        mock.call(5)
        verify { mock.call(any()) }
        clearMocks(mock)

        every { mock.call(any()) } returns 2
        mock.call(5)
        mock.call(5)
        verify(exactly = 2) { mock.call(any()) }
        clearMocks(mock)

        every { mock.call(any()) } returns 2
        mock.call(5)
        mock.call(5)
        mock.call(5)
        verify(atLeast = 2, atMost = 4) { mock.call(any()) }
        clearMocks(mock)

        every { mock.call(any()) } returns 2
        verify { mock wasNot Called }
        clearMocks(mock)

        every { mock.call(any()) } returns 2
        mock.call(5)
        verifyAll { mock.call(5) }
        clearMocks(mock)

        every { mock.call(any()) } returns 2
        mock.call(1)
        mock.call(2)
        mock.call(3)
        verifyOrder {
            mock.call(1)
            mock.call(3)
        }
        clearMocks(mock)

        every { mock.call(any()) } returns 2
        mock.call(1)
        mock.call(3)
        verifySequence {
            mock.call(1)
            mock.call(3)
        }
        clearMocks(mock)

        every { mock.call(any()) } returns 2
        mock.call(1)
        verify {
            mock.call(1)
        }
        confirmVerified(mock)
    }
}
