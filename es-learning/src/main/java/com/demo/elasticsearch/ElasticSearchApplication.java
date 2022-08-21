package com.demo.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class ElasticSearchApplication {

    public static void main(String[] args) {
          SpringApplication.run(ElasticSearchApplication.class, args);
    }
}
