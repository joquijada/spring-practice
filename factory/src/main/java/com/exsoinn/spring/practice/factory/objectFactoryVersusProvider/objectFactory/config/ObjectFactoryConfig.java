package com.exsoinn.spring.practice.factory.objectFactoryVersusProvider.objectFactory.config;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import com.exsoinn.spring.practice.factory.objectFactoryVersusProvider.objectFactory.MyObjectFactoryProtoTypedBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ObjectFactoryConfig {
    @Bean
    @Scope(SCOPE_PROTOTYPE)
    public MyObjectFactoryProtoTypedBean myObjectFactoryProtoTypedBean() {
        return new MyObjectFactoryProtoTypedBean("Object factory produced object");
    }
}
