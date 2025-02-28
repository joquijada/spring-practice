package com.exsoinn.spring.practice.factory.objectFactory;

import com.exsoinn.spring.practice.factory.objectFactoryVersusProvider.objectFactory.MyObjectFactoryConsumer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyObjectFactoryConsumerTest {
    @Autowired
    private MyObjectFactoryConsumer myObjectFactoryConsumer;

    /**
     * Motivation for this experiment was to see why the OP of https://stackoverflow.com/questions/48554044/how-to-inject-a-custom-objectfactory-in-spring
     * isn't just declaring the injection target as 'MyDefenderObjectFactory defenderFactory' so that Spring container can inject
     * by type ¯\_(ツ)_/¯
     */
    @Test
    public void objectFactoryProducesCorrectly() {
        myObjectFactoryConsumer.consume();
    }
}
