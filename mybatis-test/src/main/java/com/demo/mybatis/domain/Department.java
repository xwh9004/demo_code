package com.demo.mybatis.domain;

import java.util.List;

public class Department {
    private String deptNo;
    private String deptName;
    private List<Employee> managers;

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "deptNo='" + deptNo + '\'' +
                ", deptName='" + deptName + '\'' +
                '}';
    }

    public List<Employee> getManagers() {
        return managers;
    }

    public void setManagers(List<Employee> managers) {
        this.managers = managers;
    }
}
