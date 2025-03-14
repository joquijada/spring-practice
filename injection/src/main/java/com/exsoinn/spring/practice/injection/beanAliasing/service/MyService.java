package com.exsoinn.spring.practice.injection.beanAliasing.service;

import com.exsoinn.spring.practice.injection.beanAliasing.MyInterface;
import java.util.Map;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Getter
public class MyService {
    @Qualifier("myClassOneAliasOne")
    MyInterface bean;

    Map<String, MyInterface> beans;
}
