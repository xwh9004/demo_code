package com.demo.spring.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "prototype")
public class ClassA {
    @Autowired
    private ClassB classB;

    @Override
    public String toString() {
        return "ClassA";
    }
}
