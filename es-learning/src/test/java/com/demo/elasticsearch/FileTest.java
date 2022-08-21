package com.demo.elasticsearch;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileTest {

    @Test
    public void read() throws IOException {
        List<String> contents = FileUtils.readLines(ResourceUtils.getFile("classpath:ext_dict.dic"), "UTF-8");
        System.out.println(contents.size());
    }
}
