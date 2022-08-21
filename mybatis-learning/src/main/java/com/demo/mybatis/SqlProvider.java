package com.demo.mybatis;

public class SqlProvider {

   public static String insertUser() {
      return "INSERT INTO user ( username,address,age) VALUES(#{username}, #{address},#{age})";
    }
}
