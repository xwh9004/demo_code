package com.demo.mybatis.domain;

import java.util.List;

public class DeptManager {

    private String deptNo;

    private List<Employee> managerList;

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public List<Employee> getManagerList() {
        return managerList;
    }

    public void setManagerList(List<Employee> managerList) {
        this.managerList = managerList;
    }
}
