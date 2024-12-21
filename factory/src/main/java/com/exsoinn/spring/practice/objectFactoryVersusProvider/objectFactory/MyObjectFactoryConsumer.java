package com.exsoinn.spring.practice.objectFactoryVersusProvider.objectFactory;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MyObjectFactoryConsumer {
    ObjectFactory<MyObjectFactoryProtoTypedBean> myObjectFactory;

    public void consume() {
        myObjectFactory.getObject().printMember();
    }
}
