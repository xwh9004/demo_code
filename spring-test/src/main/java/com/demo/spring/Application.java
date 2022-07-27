package com.demo.spring;

import com.demo.spring.service.ServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext application = SpringApplication.run(Application.class);
        ServiceImpl service = application.getBean(ServiceImpl.class);
        System.out.printf("service name ="+ service.getServiceName());
        System.out.printf(" group ="+ service.getGroup());
    }
}
