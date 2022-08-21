package com.learning.io.dma;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class IOTest {
    static String path = "D:\\我的照片";
    static String fileName = "张娜梅";
    static String suffix = ".zip";
    static String filePath = path.concat("\\").concat(fileName).concat(suffix);
    //读取的文件，大小为1G
    static private File file = new File(filePath);
    //缓冲大小1024字节
    static private final int BUFFER_SIZE = 1024;

    //内存映射文件
    public static void MapTest() throws Exception {
        FileInputStream in = new FileInputStream(file);
        FileChannel channel = in.getChannel();
        //分配88M的直接内存
        MappedByteBuffer buff = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
        byte[] b = new byte[1024];
        int len = (int) file.length();


        long begin = System.currentTimeMillis();

        for (int offset = 0; offset < len; offset += 1024) {
            if (len - offset > BUFFER_SIZE) {
                buff.get(b);
            } else {
                buff.get(new byte[len - offset]);
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("map time is:" + (end - begin) + "ms");
    }

    //直接内存
    public static void DirectTest() throws Exception {
        FileInputStream in = new FileInputStream(file);
        FileChannel channel = in.getChannel();
        //分配1KB直接内存
        ByteBuffer buff = ByteBuffer.allocateDirect(BUFFER_SIZE);

        long begin = System.currentTimeMillis();

        while (channel.read(buff) != -1) {
            buff.flip();
            buff.clear();
        }

        long end = System.currentTimeMillis();
        System.out.println("Direct time is:" + (end - begin) + "ms");
    }

    //堆内存
    public static void HeapTest() throws Exception {
        FileInputStream in = new FileInputStream(file);
        FileChannel channel = in.getChannel();
        ByteBuffer buff = ByteBuffer.allocate(BUFFER_SIZE);//分配1KB堆内存

        long begin = System.currentTimeMillis();

        while (channel.read(buff) != -1) {
            buff.flip();
            buff.clear();
        }

        long end = System.currentTimeMillis();
        System.out.println("heap time is:" + (end - begin) + "ms");
    }

    public static void main(String[] args) throws Exception {
        MapTest();
        DirectTest();
        HeapTest();
    }
}
