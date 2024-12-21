package com.exsoinn.spring.practice.injection.matchingVariableAndBeanName.component;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Component
@Getter
public class MatchingVariableAndBeanNameComponentOne {
    String name = "I'm MatchingVariableAndBeanNameComponentOne";
}
