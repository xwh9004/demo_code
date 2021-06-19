package com.example.spring.service;


import org.springframework.stereotype.Service;

@Service
public class ServiceImpl {

    private String serviceName;

    public ServiceImpl(String serviceName){
        this.serviceName = serviceName;
    }

}