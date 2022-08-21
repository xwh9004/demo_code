package com.demo.spring.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ServiceImpl {

    @Value("${service.name}")
    private String serviceName;
    @Value("${service.group}")
    private String group;


    public ServiceImpl(){
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getGroup() {
        return group;
    }

}