package de.tooltime.mockks

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class GreetingControllerUnitTest {

    @MockK
    private lateinit var greetingService: GreetingService

    @InjectMockKs
    private lateinit var greetingController: GreetingController

    @Test
    fun greet() {
        every { greetingService.greet(any()) } returns "Hi John"

        assertEquals("Hi John", greetingController.greet("Joe"))
    }
}
