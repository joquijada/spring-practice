package com.exsoinn.spring.practice.injection.beanAliasing.config;

import com.exsoinn.spring.practice.injection.beanAliasing.MyClassOne;
import com.exsoinn.spring.practice.injection.beanAliasing.MyClassTwo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanAliasingConfig {
    @Bean({"myClassOneAliasOne", "myClassOneAliasTwo"})
    public MyClassOne myClassOne() {
        return new MyClassOne();
    }

    @Bean
    public MyClassTwo myClassTwo() {
        return new MyClassTwo();
    }
}
