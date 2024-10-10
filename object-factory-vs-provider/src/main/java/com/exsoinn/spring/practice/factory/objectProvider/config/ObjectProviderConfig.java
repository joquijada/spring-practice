package com.exsoinn.spring.practice.factory.objectProvider.config;

import com.exsoinn.spring.practice.factory.objectProvider.MyObjectProviderProtoTypedBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Configuration
public class ObjectProviderConfig {
    @Bean
    @Scope(SCOPE_PROTOTYPE)
    public MyObjectProviderProtoTypedBean myObjectProviderProtoTypedBean(String arg) {
        return new MyObjectProviderProtoTypedBean(arg);
    }

    @Bean
    @Scope(SCOPE_PROTOTYPE)
    public MyObjectProviderProtoTypedBean myProtoTypedBeanOne() {
        return new MyObjectProviderProtoTypedBean("one");
    }

    @Bean
    @Scope(SCOPE_PROTOTYPE)
    public MyObjectProviderProtoTypedBean myProtoTypedBeanTwo() {
        return new MyObjectProviderProtoTypedBean("two");
    }
}
