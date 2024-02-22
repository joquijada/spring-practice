package com.exsoinn.spring.practice.factory.objectFactory;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MyObjectFactoryProtoTypedBean {
    private final String member;

    public void printMember() {
        System.out.println(this.member);
    }
}
