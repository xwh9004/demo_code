package com.demo.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class DataDemo {
    @ExcelProperty(index = 0)
    private String column0;
    @ExcelProperty(index = 1)
    private String column1;
    @ExcelProperty(index = 2)
    private String column2;
    @ExcelProperty(index = 3)
    private String column3;
    @ExcelProperty(index = 4)
    private String column4;
    @ExcelProperty(index = 5)
    private String column5;
    @ExcelProperty(index = 6)
    private String column6;
    @ExcelProperty(index = 7)
    private String column7;
    @ExcelProperty(index = 8)
    private String column8;
    @ExcelProperty(index = 9)
    private String column9;
}
