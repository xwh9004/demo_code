package com.demo.io.dma;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.channels.FileChannel;

public class ZeroCopyFileDemo {

    public static void main(String[] args) {
        zeroCopy();
        normalCopy();
    }

    public static void zeroCopy() {
        String path = "D:\\test\\我的照片\\张娜梅";
        String fileName = "张娜梅_批量发片";
        String suffix = ".rar";
        String from = path.concat("\\").concat(fileName).concat(suffix);
        String to = path.concat("\\").concat(fileName).concat("_zero_copy").concat(suffix);
        try (FileChannel fromChannel = new RandomAccessFile(from, "rw").getChannel();
             FileChannel toChannel = new RandomAccessFile(to, "rw").getChannel();) {
            long position = 0;
            long count = fromChannel.size();
            System.out.println("count ="+count);
            long start = System.currentTimeMillis();
            while (position < count){
                long len =Math.min(count, 2147483647L);
                long sendCt =fromChannel.transferTo(position, len, toChannel);
                position =position+sendCt;
            }
            long end = System.currentTimeMillis();
            System.out.println("zero copy consume " + (end - start) + "ms");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static void normalCopy() {
        String path = "D:\\test\\我的照片\\张娜梅";
        String fileName = "张娜梅_批量发片";
        String suffix = ".rar";
        String from = path.concat("\\").concat(fileName).concat(suffix);
        String to = path.concat("\\").concat(fileName).concat("_normal_copy").concat(suffix);
        File fromFile = new File(from);
        File toFile = new File(to);
        long start = System.currentTimeMillis();
        try {
            long count = fromFile.length();
            System.out.println("count ="+count);
            FileUtils.copyFile(fromFile, toFile);
            long end = System.currentTimeMillis();
            System.out.println("normal copy consume " + (end - start) + "ms");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

}
