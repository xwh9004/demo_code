package com.demo.mybatis.domain;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private String address;
    private Integer age;
}
