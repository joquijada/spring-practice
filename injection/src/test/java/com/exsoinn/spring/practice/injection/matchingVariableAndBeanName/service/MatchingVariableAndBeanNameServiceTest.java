package com.exsoinn.spring.practice.injection.matchingVariableAndBeanName.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MatchingVariableAndBeanNameServiceTest {
    @Autowired
    private MatchingVariableAndBeanNameService matchingVariableAndBeanNameService;

    @Test
    void componentsAreAutowiredBasedOnMatchingVariableAndBeanName() {
        assertNotNull(matchingVariableAndBeanNameService.getMatchingVariableAndBeanNameComponentOne());
        assertEquals(
                "I'm MatchingVariableAndBeanNameComponentOne",
                matchingVariableAndBeanNameService
                        .getMatchingVariableAndBeanNameComponentOne()
                        .getName());
    }
}
