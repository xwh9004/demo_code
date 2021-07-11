package com.demo.springtest;

import com.demo.springtest.beans.ClassA;
import org.springframework.beans.factory.BeanCurrentlyInCreationException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringTestApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringTestApplication.class);
        ClassA classA =applicationContext.getBean(ClassA.class);
        System.out.println(classA);
    }
}
