package com.exsoinn.spring.practice.factory.objectFactoryVersusProvider.objectProvider;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MyObjectProviderProtoTypedBean {
    String member;

    MyObjectProviderProtoTypedBean printMember() {
        System.out.println(this.member);
        return this;
    }

    MyObjectProviderProtoTypedBean getThis() {
        return this;
    }
}
