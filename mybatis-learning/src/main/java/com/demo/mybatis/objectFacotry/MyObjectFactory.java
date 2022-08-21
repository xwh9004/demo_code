package com.demo.mybatis.objectFacotry;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

public class MyObjectFactory extends DefaultObjectFactory {

    private String className ="com.demo.mybatis.domain.User";

    @Override
    public <T> T create(Class<T> type) {
        if(type.getName().equals(className)){
            doSomething();
        }

        return create(type, null, null);
    }

    private void doSomething() {
        System.out.println("User created");
    }
}
