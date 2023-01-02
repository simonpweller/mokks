package de.tooltime.mockks

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/greet/")
class GreetingController(private val greetingService: GreetingService) {

    @GetMapping("/{name}")
    fun greet(@PathVariable name: String) = greetingService.greet(name)
}
