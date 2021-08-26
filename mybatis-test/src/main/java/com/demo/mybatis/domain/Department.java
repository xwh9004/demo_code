package com.demo.mybatis.domain;

import lombok.Data;

import java.util.List;
@Data
public class Department {
    private String deptNo;
    private String deptName;
    private List<Employee> managers;
}
