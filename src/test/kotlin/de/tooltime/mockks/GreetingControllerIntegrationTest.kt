package de.tooltime.mockks

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest
internal class GreetingControllerIntegrationTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    private lateinit var greetingService: GreetingService

    @Test
    fun greet() {
        every { greetingService.greet(any()) } returns "Hi John"
        val response = mockMvc.perform(get("/greet/Joe")).andExpect(status().isOk).andReturn().response
        assertEquals("Hi John", response.contentAsString)
    }
}
