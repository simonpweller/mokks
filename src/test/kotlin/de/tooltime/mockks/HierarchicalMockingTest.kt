package de.tooltime.mockks

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class UserService() {
    fun getUsers(): List<User> = emptyList()
}

class User(
    val firstName: String,
    val lastName: String
)

internal class HierarchicalMockingTest {
    @Test
    fun `hierarchical mocks work`() {
        val userService = mockk<UserService>()
        every { userService.getUsers() } returns listOf(
            mockk { every { firstName } returns "John" }
        )

        assertEquals("John", userService.getUsers().first().firstName)
    }
}
