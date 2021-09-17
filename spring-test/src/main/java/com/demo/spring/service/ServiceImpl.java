package com.demo.spring.service;


import org.springframework.stereotype.Service;

@Service
public class ServiceImpl {

    private String serviceName;

    public ServiceImpl(){
        this.serviceName = "hello Service";
    }

}