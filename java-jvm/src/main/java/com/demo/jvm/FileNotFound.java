package com.demo.jvm;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class FileNotFound {
    private FileOutputStream outputStream = new FileOutputStream("test.txt");

    /**
     * JVM在调用构造方法时，会讲声明在构造方法以外的所有语句都放在<init> 方法中，
     * 故所有受查异常都需要在构造方法上声明
     * @throws FileNotFoundException
     */
    public FileNotFound() throws FileNotFoundException {

    }

}
