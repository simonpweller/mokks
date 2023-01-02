package de.tooltime.mockks

import org.springframework.stereotype.Service

@Service
class GreetingService {
    fun greet(name: String): String = "Hi $name"
}
