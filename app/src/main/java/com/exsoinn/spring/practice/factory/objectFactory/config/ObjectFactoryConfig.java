package com.exsoinn.spring.practice.factory.objectFactory.config;

import com.exsoinn.spring.practice.factory.objectFactory.MyObjectFactoryProtoTypedBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Configuration
public class ObjectFactoryConfig {
    @Bean
    @Scope(SCOPE_PROTOTYPE)
    public MyObjectFactoryProtoTypedBean myObjectFactoryProtoTypedBean() {
        return new MyObjectFactoryProtoTypedBean("Object factory produced object");
    }
}
