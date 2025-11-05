package com.exsoinn.spring.practice.mustache.service;

import static org.junit.jupiter.api.Assertions.*;

import com.exsoinn.spring.practice.mustache.model.Greeting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for GreetingService.
 * Tests the business logic layer in isolation.
 */
class GreetingServiceTest {

    private GreetingService greetingService;

    @BeforeEach
    void setUp() {
        greetingService = new GreetingService();
    }

    @Test
    @DisplayName("Should return default greeting with 'Hello' and 'World'")
    void testGetDefaultGreeting() {
        // When
        Greeting greeting = greetingService.getDefaultGreeting();

        // Then
        assertNotNull(greeting);
        assertEquals("Hello", greeting.getMessage());
        assertEquals("World", greeting.getRecipient());
        assertEquals("Hello, World!", greeting.getFullGreeting());
    }

    @Test
    @DisplayName("Should return personalized greeting with provided name")
    void testGetPersonalizedGreeting() {
        // When
        Greeting greeting = greetingService.getPersonalizedGreeting("Alice");

        // Then
        assertNotNull(greeting);
        assertEquals("Hello", greeting.getMessage());
        assertEquals("Alice", greeting.getRecipient());
        assertEquals("Hello, Alice!", greeting.getFullGreeting());
    }

    @Test
    @DisplayName("Should return default greeting when name is null")
    void testGetPersonalizedGreetingWithNullName() {
        // When
        Greeting greeting = greetingService.getPersonalizedGreeting(null);

        // Then
        assertNotNull(greeting);
        assertEquals("Hello", greeting.getMessage());
        assertEquals("World", greeting.getRecipient());
        assertEquals("Hello, World!", greeting.getFullGreeting());
    }

    @Test
    @DisplayName("Should return default greeting when name is empty")
    void testGetPersonalizedGreetingWithEmptyName() {
        // When
        Greeting greeting = greetingService.getPersonalizedGreeting("   ");

        // Then
        assertNotNull(greeting);
        assertEquals("Hello", greeting.getMessage());
        assertEquals("World", greeting.getRecipient());
        assertEquals("Hello, World!", greeting.getFullGreeting());
    }

    @Test
    @DisplayName("Should trim whitespace from personalized greeting name")
    void testGetPersonalizedGreetingTrimsWhitespace() {
        // When
        Greeting greeting = greetingService.getPersonalizedGreeting("  Bob  ");

        // Then
        assertNotNull(greeting);
        assertEquals("Hello", greeting.getMessage());
        assertEquals("Bob", greeting.getRecipient());
        assertEquals("Hello, Bob!", greeting.getFullGreeting());
    }

    @Test
    @DisplayName("Should return custom greeting with both message and recipient")
    void testGetCustomGreeting() {
        // When
        Greeting greeting = greetingService.getCustomGreeting("Welcome", "Charlie");

        // Then
        assertNotNull(greeting);
        assertEquals("Welcome", greeting.getMessage());
        assertEquals("Charlie", greeting.getRecipient());
        assertEquals("Welcome, Charlie!", greeting.getFullGreeting());
    }

    @Test
    @DisplayName("Should use default message when custom message is null")
    void testGetCustomGreetingWithNullMessage() {
        // When
        Greeting greeting = greetingService.getCustomGreeting(null, "Dave");

        // Then
        assertNotNull(greeting);
        assertEquals("Hello", greeting.getMessage());
        assertEquals("Dave", greeting.getRecipient());
        assertEquals("Hello, Dave!", greeting.getFullGreeting());
    }

    @Test
    @DisplayName("Should use default recipient when custom recipient is null")
    void testGetCustomGreetingWithNullRecipient() {
        // When
        Greeting greeting = greetingService.getCustomGreeting("Greetings", null);

        // Then
        assertNotNull(greeting);
        assertEquals("Greetings", greeting.getMessage());
        assertEquals("World", greeting.getRecipient());
        assertEquals("Greetings, World!", greeting.getFullGreeting());
    }

    @Test
    @DisplayName("Should use defaults when both message and recipient are null")
    void testGetCustomGreetingWithNullBoth() {
        // When
        Greeting greeting = greetingService.getCustomGreeting(null, null);

        // Then
        assertNotNull(greeting);
        assertEquals("Hello", greeting.getMessage());
        assertEquals("World", greeting.getRecipient());
        assertEquals("Hello, World!", greeting.getFullGreeting());
    }

    @Test
    @DisplayName("Should trim whitespace from custom greeting parameters")
    void testGetCustomGreetingTrimsWhitespace() {
        // When
        Greeting greeting = greetingService.getCustomGreeting("  Hi  ", "  Eve  ");

        // Then
        assertNotNull(greeting);
        assertEquals("Hi", greeting.getMessage());
        assertEquals("Eve", greeting.getRecipient());
        assertEquals("Hi, Eve!", greeting.getFullGreeting());
    }

    @Test
    @DisplayName("Should use defaults when both message and recipient are empty")
    void testGetCustomGreetingWithEmptyStrings() {
        // When
        Greeting greeting = greetingService.getCustomGreeting("   ", "   ");

        // Then
        assertNotNull(greeting);
        assertEquals("Hello", greeting.getMessage());
        assertEquals("World", greeting.getRecipient());
        assertEquals("Hello, World!", greeting.getFullGreeting());
    }
}
