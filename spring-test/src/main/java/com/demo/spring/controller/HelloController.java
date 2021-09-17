package com.demo.spring.controller;


import com.demo.excel.ExcelUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/hello/")
public class HelloController {
    @RequestMapping(path = "hi", method = RequestMethod.GET)
    public String hi(@RequestParam(value = "fileName") String name,@RequestParam(value = "size") int size) {
        String fileName = "D://excel/"+name+".xlsx";
        long start =System.currentTimeMillis();
        ExcelUtil.read(fileName,size);
        System.out.println("耗时："+ (System.currentTimeMillis() - start)+"ms");
        return "helloworld";
    }
}
