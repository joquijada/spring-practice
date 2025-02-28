package com.exsoinn.spring.practice.factory.objectFactoryVersusProvider.objectProvider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyObjectProviderConsumerTest {
    @Autowired
    private MyObjectProviderConsumer myObjectProviderConsumer;

    @Test
    void objectProviderProducesCorrectly() {
        String payload = "Object provider produced object - TEST";
        assertEquals(payload, myObjectProviderConsumer.consume(payload).getMember());
        assertEquals(
                payload + payload,
                myObjectProviderConsumer.consume(payload + payload).getMember());
    }

    /**
     * Because the bean that the ObjectProvider produces
     */
    @Test
    void differentObjectProviderInstancesAreProducedEachTime() {
        assertNotEquals(
                myObjectProviderConsumer.consume("").getThis(),
                myObjectProviderConsumer.consume("").getThis());
    }

    /**
     * Validates that indeed Spring is dispensing unique instances when requesting beans
     * marked as prototyped.
     */
    @Test
    void beanGetsPrototypedCorrectly() {
        assertNotEquals(
                myObjectProviderConsumer.getMyProtoTypedBeanOne(), myObjectProviderConsumer.getMyProtoTypedBeanThree());
    }
}
