package com.demo.mybatis.domain;

import lombok.Data;

import java.util.Date;
@Data
public class Employee {

    private Long empNo;

    private Date birthDate;

    private String firstName;

    private String lastName;

    private String gender;

    private Date hireDate;

}
