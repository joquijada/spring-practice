package com.exsoinn.spring.practice.mustache;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.exsoinn.spring.practice.mustache.model.Greeting;
import com.exsoinn.spring.practice.mustache.service.GreetingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Unit tests for HelloController.
 * Uses MockMvc to test the controller layer in isolation with mocked service.
 */
@WebMvcTest(HelloController.class)
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GreetingService greetingService;

    @Test
    @DisplayName("GET / should return hello view with default greeting message")
    void testHello() throws Exception {
        // Given
        Greeting greeting = new Greeting("Hello", "World");
        when(greetingService.getDefaultGreeting()).thenReturn(greeting);

        // When & Then
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("hello"))
                .andExpect(model().attributeExists("message"))
                .andExpect(model().attribute("message", "Hello, World!"));
    }

    @Test
    @DisplayName("GET /greet without parameter should return hello view with default greeting")
    void testGreetWithoutParameter() throws Exception {
        // Given
        Greeting greeting = new Greeting("Hello", "World");
        when(greetingService.getPersonalizedGreeting(null)).thenReturn(greeting);

        // When & Then
        mockMvc.perform(get("/greet"))
                .andExpect(status().isOk())
                .andExpect(view().name("hello"))
                .andExpect(model().attributeExists("message"))
                .andExpect(model().attribute("message", "Hello, World!"));
    }

    @Test
    @DisplayName("GET /greet with name parameter should return personalized greeting")
    void testGreetWithName() throws Exception {
        // Given
        Greeting greeting = new Greeting("Hello", "Alice");
        when(greetingService.getPersonalizedGreeting("Alice")).thenReturn(greeting);

        // When & Then
        mockMvc.perform(get("/greet").param("name", "Alice"))
                .andExpect(status().isOk())
                .andExpect(view().name("hello"))
                .andExpect(model().attributeExists("message"))
                .andExpect(model().attribute("message", "Hello, Alice!"));
    }

    @Test
    @DisplayName("GET /custom without parameters should return default custom greeting")
    void testCustomGreetingWithoutParameters() throws Exception {
        // Given
        Greeting greeting = new Greeting("Hello", "World");
        when(greetingService.getCustomGreeting(null, null)).thenReturn(greeting);

        // When & Then
        mockMvc.perform(get("/custom"))
                .andExpect(status().isOk())
                .andExpect(view().name("hello"))
                .andExpect(model().attributeExists("message"))
                .andExpect(model().attribute("message", "Hello, World!"));
    }

    @Test
    @DisplayName("GET /custom with message parameter should return custom message")
    void testCustomGreetingWithMessage() throws Exception {
        // Given
        Greeting greeting = new Greeting("Welcome", "World");
        when(greetingService.getCustomGreeting("Welcome", null)).thenReturn(greeting);

        // When & Then
        mockMvc.perform(get("/custom").param("message", "Welcome"))
                .andExpect(status().isOk())
                .andExpect(view().name("hello"))
                .andExpect(model().attributeExists("message"))
                .andExpect(model().attribute("message", "Welcome, World!"));
    }

    @Test
    @DisplayName("GET /custom with name parameter should return greeting with custom recipient")
    void testCustomGreetingWithName() throws Exception {
        // Given
        Greeting greeting = new Greeting("Hello", "Bob");
        when(greetingService.getCustomGreeting(null, "Bob")).thenReturn(greeting);

        // When & Then
        mockMvc.perform(get("/custom").param("name", "Bob"))
                .andExpect(status().isOk())
                .andExpect(view().name("hello"))
                .andExpect(model().attributeExists("message"))
                .andExpect(model().attribute("message", "Hello, Bob!"));
    }

    @Test
    @DisplayName("GET /custom with both parameters should return fully custom greeting")
    void testCustomGreetingWithBothParameters() throws Exception {
        // Given
        Greeting greeting = new Greeting("Greetings", "Charlie");
        when(greetingService.getCustomGreeting("Greetings", "Charlie")).thenReturn(greeting);

        // When & Then
        mockMvc.perform(get("/custom").param("message", "Greetings").param("name", "Charlie"))
                .andExpect(status().isOk())
                .andExpect(view().name("hello"))
                .andExpect(model().attributeExists("message"))
                .andExpect(model().attribute("message", "Greetings, Charlie!"));
    }
}
