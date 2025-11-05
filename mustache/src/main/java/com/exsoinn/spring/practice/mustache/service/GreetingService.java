package com.exsoinn.spring.practice.mustache.service;

import com.exsoinn.spring.practice.mustache.model.Greeting;
import org.springframework.stereotype.Service;

/**
 * Service layer for handling greeting business logic.
 * This separates business logic from the controller layer.
 */
@Service
public class GreetingService {

    private static final String DEFAULT_MESSAGE = "Hello";
    private static final String DEFAULT_RECIPIENT = "World";

    /**
     * Creates a default greeting.
     *
     * @return a Greeting with default message and recipient
     */
    public Greeting getDefaultGreeting() {
        return new Greeting(DEFAULT_MESSAGE, DEFAULT_RECIPIENT);
    }

    /**
     * Creates a personalized greeting for a specific recipient.
     *
     * @param recipient the person to greet
     * @return a personalized Greeting
     */
    public Greeting getPersonalizedGreeting(String recipient) {
        if (recipient == null || recipient.trim().isEmpty()) {
            return getDefaultGreeting();
        }
        return new Greeting(DEFAULT_MESSAGE, recipient.trim());
    }

    /**
     * Creates a custom greeting with both message and recipient.
     *
     * @param message the greeting message
     * @param recipient the person to greet
     * @return a custom Greeting
     */
    public Greeting getCustomGreeting(String message, String recipient) {
        String greetingMessage = (message == null || message.trim().isEmpty()) ? DEFAULT_MESSAGE : message.trim();
        String greetingRecipient =
                (recipient == null || recipient.trim().isEmpty()) ? DEFAULT_RECIPIENT : recipient.trim();

        return new Greeting(greetingMessage, greetingRecipient);
    }
}
