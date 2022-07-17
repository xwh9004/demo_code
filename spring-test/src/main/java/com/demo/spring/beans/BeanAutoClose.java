package com.demo.spring.beans;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class BeanAutoClose implements ApplicationRunner, AutoCloseable {

    public BeanAutoClose(){
        System.out.println("BeanAutoClose created");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Yes! BeanAutoClose created");
    }

//    @PreDestroy
    public void doClose(){
        System.out.println("BeanAutoClose destroyed");
    }

//   需要实现AutoCloseable
    public void close(){
        System.out.println("BeanAutoClose destroyed close");
    }
    public void shutDown(){
        System.out.println("BeanAutoClose destroyed close");
    }

}
