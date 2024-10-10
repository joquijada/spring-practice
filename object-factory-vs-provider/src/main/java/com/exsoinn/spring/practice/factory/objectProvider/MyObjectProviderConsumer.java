package com.exsoinn.spring.practice.factory.objectProvider;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Getter
public class MyObjectProviderConsumer {
    @Qualifier("myObjectProviderProtoTypedBean")
    private final ObjectProvider<MyObjectProviderProtoTypedBean> myObjectProvider;

    @Qualifier("myProtoTypedBeanOne")
    private final MyObjectProviderProtoTypedBean myProtoTypedBeanOne;

    @Qualifier("myProtoTypedBeanOne")
    private final MyObjectProviderProtoTypedBean myProtoTypedBeanTwo;

    /*
     * Instance member myProtoTypedBeanThree should get a difference instance than myProtoTypedBeanOne
     * because the bean is marked as @Scope(PROTOTYPE)
     */
    @Qualifier("myProtoTypedBeanOne")
    private final MyObjectProviderProtoTypedBean myProtoTypedBeanThree;

    public MyObjectProviderProtoTypedBean consume(String arg) {
        return myObjectProvider.getObject(arg).printMember();
    }
}
