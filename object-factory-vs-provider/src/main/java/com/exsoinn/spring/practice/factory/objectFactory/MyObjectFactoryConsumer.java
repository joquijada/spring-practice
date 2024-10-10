package com.exsoinn.spring.practice.factory.objectFactory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Getter
public class MyObjectFactoryConsumer {
    private final ObjectFactory<MyObjectFactoryProtoTypedBean> myObjectFactory;

    public void consume() {
        myObjectFactory.getObject().printMember();
    }
}
