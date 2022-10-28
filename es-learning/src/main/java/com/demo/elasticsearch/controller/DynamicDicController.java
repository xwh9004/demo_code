package com.demo.elasticsearch.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@RequestMapping("/es/remote_dict")
@RestController
public class DynamicDicController {


    @RequestMapping(value = "/ext_dict", method = RequestMethod.HEAD)
    public void extDictPreHand(HttpServletRequest request, HttpServletResponse response) {
        log.info("开始轮询[head]获取最新的词典");
        try {
            File file = ResourceUtils.getFile("classpath:ext_dict.dic");
            String latest_ETags = String.valueOf(file.lastModified());
            String old_ETags = request.getHeader("If-None-Match");
            String lastModified = request.getHeader("If-Modify-Since");
            String eTag = request.getHeader("ETag");
            log.info("head请求，old_ETags=" + old_ETags);
            log.info("head请求，lastModified=" + lastModified);
            log.info("head请求，eTag=" + eTag);
            response.setHeader("Etag", old_ETags);
        } catch (IOException e) {
            log.error("获取文件流出错", e);
        }


    }

    @GetMapping("/ext_dict")
    public void dynamicDic(HttpServletRequest request, HttpServletResponse response) {
        log.info("开始获取最新的词典");

        try (ServletOutputStream out = response.getOutputStream();) {
            File file = ResourceUtils.getFile("classpath:ext_dict.dic");
            response.addHeader("Last-Modified", String.valueOf(file.lastModified()));
            response.addHeader("ETag", "v1.0");
            StringBuilder sb = new StringBuilder();
            List<String> contents = FileUtils.readLines(file, "UTF-8");
            contents.stream().forEach(item -> sb.append(item).append("\n"));
            out.write(sb.toString().getBytes("UTF-8"));
            out.flush();
        } catch (IOException e) {
            log.error("获取输出流出错", e);
        }
    }

    @GetMapping("/ext_stopwords")
    public void stopWord(HttpServletRequest request, HttpServletResponse response) {
        log.info("开始获取最新的停顿词典");

        try (ServletOutputStream out = response.getOutputStream();) {
            File file = ResourceUtils.getFile("classpath:ext_stopwords.dic");
            response.addHeader("Last-Modified", String.valueOf(file.lastModified()));
            response.addHeader("ETag", "v1.0");
            StringBuilder sb = new StringBuilder();
            List<String> contents = FileUtils.readLines(file, "UTF-8");
            contents.stream().forEach(item -> sb.append(item).append("\n"));
            out.write(sb.toString().getBytes("UTF-8"));
            out.flush();
        } catch (IOException e) {
            log.error("获取输出流出错", e);
        }
    }

    @GetMapping("/ext_synonym")
    public void synonymWord(HttpServletRequest request, HttpServletResponse response) {
        log.info("开始获取最新的同义词词典");

        try (ServletOutputStream out = response.getOutputStream();) {
            File file = ResourceUtils.getFile("classpath:ext_synonym.dic");
            response.addHeader("Last-Modified", String.valueOf(file.lastModified()));
            response.addHeader("ETag", "v1.0");
            response.addHeader("Content-Type", "text/plain;charset=UTF-8");
            StringBuilder sb = new StringBuilder();
            List<String> contents = FileUtils.readLines(file, "UTF-8");
            contents.stream().forEach(item -> sb.append(item).append("\n"));
            out.write(sb.toString().getBytes("UTF-8"));
            out.flush();
            log.info("获取最新的同义词词典完成");
        } catch (IOException e) {
            log.error("获取输出流出错", e);
        }
    }
}
