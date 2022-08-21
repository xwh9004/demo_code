package com.demo.spring.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClassB {

    @Autowired
    private ClassA classA;

    @Override
    public String toString() {
        return "ClassB";
    }
}
