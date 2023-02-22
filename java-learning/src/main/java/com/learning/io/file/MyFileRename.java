package com.learning.io.file;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author: xwh90
 * @date: 2023/2/21 17:42
 * @description:
 */
public class MyFileRename {

    private static Map<String, File> renameFileMap = new HashMap<>();
    private static Map<String, File> existFileMap = new HashMap<>();
    private static Set<String> existFileName = new HashSet<>();
    private static Map<String, String> newNameMap = new HashMap<>();
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_hhmmss");

    public static void main(String[] args) throws FileNotFoundException {
        File dir = new File("D:\\我的照片\\许俊屹\\视频");

        for (File file : dir.listFiles()) {
            final String fileName = file.getName();
            if (fileName.startsWith("mmexport")) {
                renameFileMap.put(fileName, file);
            } else {
                existFileMap.put(fileName, file);
            }
        }

        final Set<String> keys = renameFileMap.keySet();

        for (String key : keys) {
            String newName = newName(key);
            while (!existFileName.add(newName)) {
                newName = getNextName(newName);
            }
            newNameMap.put(key, newName);
        }
        newNameMap.forEach((k,v)->{
            final File source = renameFileMap.get(k);
            final File dest = new File(dir, v);
            source.renameTo(dest);
        });
    }

    private static String getNextName(String fileName) {
        int index = fileName.indexOf(".");
        String fileType = fileName.substring(index);
        String part = fileName.substring(0, fileName.length() - fileType.length());
        return part.concat("_").concat("1").concat(fileType);
    }

    private static String newName(String fileName) {
        int index = fileName.indexOf(".");
        String fileType = fileName.substring(index);
        String timePart = fileName.substring("mmexport".length(), fileName.length() - fileType.length());
        Date date = new Date(Long.valueOf(timePart));
        String newFileName = formatter.format(date);
        newFileName = newFileName.concat(fileType);
        return newFileName;
    }
}
