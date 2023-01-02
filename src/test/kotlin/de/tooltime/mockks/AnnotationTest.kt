package de.tooltime.mockks

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class AnnotationTest {

    @MockK
    lateinit var strictMock: Dependency1

    @RelaxedMockK
    lateinit var relaxedMock: Dependency1

    @SpyK
    var spy = Dependency1(1)

    @Test
    fun `mocks are auto constructed`() {
        every { strictMock.value1 } returns 2
        assertEquals(2, strictMock.value1)
        assertEquals(0, relaxedMock.value1)
        assertEquals(1, spy.value1)
        verify { spy.value1 }
    }
}
