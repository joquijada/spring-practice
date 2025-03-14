package com.exsoinn.spring.practice.injection.mapOfBeans.service;

import com.exsoinn.spring.practice.injection.mapOfBeans.MyInterface;
import java.util.Map;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Getter
public class MyService {
    Map<String, MyInterface> beans;
}
