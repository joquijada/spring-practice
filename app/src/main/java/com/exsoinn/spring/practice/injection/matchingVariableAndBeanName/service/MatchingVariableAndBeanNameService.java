package com.exsoinn.spring.practice.injection.matchingVariableAndBeanName.service;

import com.exsoinn.spring.practice.injection.matchingVariableAndBeanName.component.MatchingVariableAndBeanNameComponentOne;
import com.exsoinn.spring.practice.injection.matchingVariableAndBeanName.component.MatchingVariableAndBeanNameComponentTwo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Getter
public class MatchingVariableAndBeanNameService {
    MatchingVariableAndBeanNameComponentOne matchingVariableAndBeanNameComponentOne;
    MatchingVariableAndBeanNameComponentTwo matchingVariableAndBeanNameComponentTwo;
}
