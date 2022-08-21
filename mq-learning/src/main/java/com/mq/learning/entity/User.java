package com.mq.learning.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: xwh90
 * @date: 2022/8/21 10:59
 * @description:
 */
@Data
public class User implements Serializable {

   private String username;
   private   int age;

    public User(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
