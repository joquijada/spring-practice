package com.exsoinn.spring.practice.mustache.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class representing a greeting message.
 * This is the "M" in MVC pattern.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Greeting {
    private String message;
    private String recipient;

    /**
     * Creates a greeting with just a message.
     *
     * @param message the greeting message
     */
    public Greeting(String message) {
        this.message = message;
        this.recipient = "World";
    }

    /**
     * Returns the full greeting text.
     *
     * @return formatted greeting
     */
    public String getFullGreeting() {
        if (recipient != null && !recipient.isEmpty()) {
            return message + ", " + recipient + "!";
        }
        return message;
    }
}
