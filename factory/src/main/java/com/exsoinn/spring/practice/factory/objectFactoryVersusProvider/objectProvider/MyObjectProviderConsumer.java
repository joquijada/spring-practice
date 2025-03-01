package com.exsoinn.spring.practice.factory.objectFactoryVersusProvider.objectProvider;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MyObjectProviderConsumer {
    @Qualifier("myObjectProviderProtoTypedBean")
    ObjectProvider<MyObjectProviderProtoTypedBean> myObjectProvider;

    @Qualifier("myProtoTypedBeanOne")
    MyObjectProviderProtoTypedBean myProtoTypedBeanOne;

    @Qualifier("myProtoTypedBeanOne")
    MyObjectProviderProtoTypedBean myProtoTypedBeanTwo;

    /*
     * Instance member myProtoTypedBeanThree should get a difference instance than myProtoTypedBeanOne
     * because the bean is marked as @Scope(PROTOTYPE)
     */
    @Qualifier("myProtoTypedBeanOne")
    MyObjectProviderProtoTypedBean myProtoTypedBeanThree;

    public MyObjectProviderProtoTypedBean consume(String arg) {
        return myObjectProvider.getObject(arg).printMember();
    }
}
