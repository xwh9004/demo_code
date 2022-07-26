package com.demo.spring.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping(value = "/hello/")
public class HelloController {
    @RequestMapping(path = "hi", method = RequestMethod.GET)
    public String hi(@RequestParam(value = "fileName") String name,@RequestParam(value = "size") int size) {
        String fileName = "D://excel/"+name+".xlsx";
        long start =System.currentTimeMillis();
        System.out.println("耗时："+ (System.currentTimeMillis() - start)+"ms");
        return "hello world";
    }

    @RequestMapping(path = "ok", method = RequestMethod.GET)
    public String hi() {
        return "ok";
    }

    @RequestMapping(path = "needAuth", method = RequestMethod.GET)
    public void needAuth(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(401);
        response.addHeader("WWW-Authenticate","Basic realm=Realm");
    }
}
