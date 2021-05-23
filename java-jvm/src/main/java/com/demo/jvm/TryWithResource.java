package com.demo.jvm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TryWithResource {

    public static void main(String[] args) {

    }
    public static void foo() throws IOException {
        try (FileInputStream in = new FileInputStream("text.txt")){
            in.read();
        }
    }
}
