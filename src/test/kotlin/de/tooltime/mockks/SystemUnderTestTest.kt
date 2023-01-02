package de.tooltime.mockks

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class SystemUnderTestTest {

    @Nested
    @DisplayName("calculate")
    inner class Calculate {
        @Test
        fun `adds values (without mocks)`() {
            val doc1 = Dependency1(5)
            val doc2 = Dependency2("6")

            val sut = SystemUnderTest(doc1, doc2)

            assertEquals(11, sut.calculate())
        }

        @Test
        fun `adds values (with mocks)`() {
            val doc1 = mockk<Dependency1>()
            val doc2 = mockk<Dependency2>()

            every { doc1.value1 } returns 5
            every { doc2.value2 } returns "6"

            val sut = SystemUnderTest(doc1, doc2)

            assertEquals(11, sut.calculate())
        }
    }
}
