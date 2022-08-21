package com.demo.mybatis.domain;

import lombok.Data;

import java.util.List;
@Data
public class DeptManager {

    private String deptNo;

    private List<Employee> managerList;
}
