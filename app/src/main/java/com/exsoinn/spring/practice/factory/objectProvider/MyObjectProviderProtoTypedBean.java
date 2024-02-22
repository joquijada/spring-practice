package com.exsoinn.spring.practice.factory.objectProvider;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MyObjectProviderProtoTypedBean {
    private final String member;

    public MyObjectProviderProtoTypedBean printMember() {
        System.out.println(this.member);
        return this;
    }
}
