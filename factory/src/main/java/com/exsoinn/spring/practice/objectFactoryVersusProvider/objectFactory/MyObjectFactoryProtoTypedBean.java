package com.exsoinn.spring.practice.objectFactoryVersusProvider.objectFactory;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MyObjectFactoryProtoTypedBean {
    String member;

    public void printMember() {
        System.out.println(this.member);
    }
}
