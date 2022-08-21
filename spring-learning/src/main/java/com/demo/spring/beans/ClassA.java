package com.demo.spring.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value ="classA")
public class ClassA {
    @Autowired
    private ClassB classB;

    @Override
    public String toString() {
        return "ClassA";
    }
}
