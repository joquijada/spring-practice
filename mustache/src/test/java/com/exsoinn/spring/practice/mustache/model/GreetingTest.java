package com.exsoinn.spring.practice.mustache.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Greeting model class.
 */
class GreetingTest {

    @Test
    @DisplayName("Should create greeting with message and recipient")
    void testGreetingWithBothParameters() {
        // When
        Greeting greeting = new Greeting("Hello", "Alice");

        // Then
        assertEquals("Hello", greeting.getMessage());
        assertEquals("Alice", greeting.getRecipient());
        assertEquals("Hello, Alice!", greeting.getFullGreeting());
    }

    @Test
    @DisplayName("Should create greeting with message only and default recipient")
    void testGreetingWithMessageOnly() {
        // When
        Greeting greeting = new Greeting("Hello");

        // Then
        assertEquals("Hello", greeting.getMessage());
        assertEquals("World", greeting.getRecipient());
        assertEquals("Hello, World!", greeting.getFullGreeting());
    }

    @Test
    @DisplayName("Should create greeting with no-arg constructor")
    void testGreetingNoArgConstructor() {
        // When
        Greeting greeting = new Greeting();
        greeting.setMessage("Hi");
        greeting.setRecipient("Bob");

        // Then
        assertEquals("Hi", greeting.getMessage());
        assertEquals("Bob", greeting.getRecipient());
        assertEquals("Hi, Bob!", greeting.getFullGreeting());
    }

    @Test
    @DisplayName("Should return message when recipient is null")
    void testGetFullGreetingWithNullRecipient() {
        // When
        Greeting greeting = new Greeting("Hello", null);

        // Then
        assertEquals("Hello", greeting.getFullGreeting());
    }

    @Test
    @DisplayName("Should return message when recipient is empty")
    void testGetFullGreetingWithEmptyRecipient() {
        // When
        Greeting greeting = new Greeting("Hello", "");

        // Then
        assertEquals("Hello", greeting.getFullGreeting());
    }

    @Test
    @DisplayName("Should format full greeting correctly with both values")
    void testGetFullGreetingFormatting() {
        // When
        Greeting greeting = new Greeting("Good morning", "Team");

        // Then
        assertEquals("Good morning, Team!", greeting.getFullGreeting());
    }

    @Test
    @DisplayName("Should support equals and hashCode (Lombok @Data)")
    void testEqualsAndHashCode() {
        // Given
        Greeting greeting1 = new Greeting("Hello", "World");
        Greeting greeting2 = new Greeting("Hello", "World");
        Greeting greeting3 = new Greeting("Hi", "World");

        // Then
        assertEquals(greeting1, greeting2);
        assertEquals(greeting1.hashCode(), greeting2.hashCode());
        assertNotEquals(greeting1, greeting3);
    }

    @Test
    @DisplayName("Should support toString (Lombok @Data)")
    void testToString() {
        // When
        Greeting greeting = new Greeting("Hello", "World");

        // Then
        String result = greeting.toString();
        assertTrue(result.contains("Hello"));
        assertTrue(result.contains("World"));
    }
}
