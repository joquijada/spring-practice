package com.exsoinn.spring.practice.lifecycle;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;

public abstract class AbstractService {
    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        // Common logic for handling the event
        System.out.println(
                "Event received in abstract class: ");
    }
}
