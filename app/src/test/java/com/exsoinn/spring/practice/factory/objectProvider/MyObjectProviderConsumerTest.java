package com.exsoinn.spring.practice.factory.objectProvider;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class MyObjectProviderConsumerTest {
    @Autowired
    private MyObjectProviderConsumer myObjectProviderConsumer;

    @Test
    public void objectProviderProducesCorrectly() {
        String payload = "Object provider produced object - TEST";
        assertEquals(payload, myObjectProviderConsumer.consume(payload).getMember());
        assertEquals(payload + payload, myObjectProviderConsumer.consume(payload + payload).getMember());
    }

    /**
     * Validates that indeed Spring is dispensing unique instances when requesting beans
     * marked as prototyped.
     */
    @Test
    public void beanGetsPrototypedCorrectly() {
        assertNotEquals(myObjectProviderConsumer.getMyProtoTypedBeanOne(), myObjectProviderConsumer.getMyProtoTypedBeanThree());
    }

}
