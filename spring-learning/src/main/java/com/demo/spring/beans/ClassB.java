package com.demo.spring.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "prototype")
public class ClassB {

    @Autowired
    private ClassA classA;

    @Override
    public String toString() {
        return "ClassB";
    }
}
