package com.learning.io.client;

import org.apache.commons.io.IOUtils;
import sun.nio.ch.IOUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author: xwh90
 * @date: 2023/4/21 10:36
 * @description: 使用nio发送文件
 * 代码来自于Java 高并发核心编程 第三章第四节 详解Nio Channel类 示例
 */
public class NioSendClient {

    private Charset charset = Charset.forName("UTF-8");

    public void sendFile(String srcFile) throws IOException {
        File file = new File(srcFile);
        if (!file.exists()) {
            System.out.println("文件不存在");
            return;
        }


        final FileChannel fileChannel = new FileInputStream(file).getChannel();

        final SocketChannel socketChannel = SocketChannel.open();

        socketChannel.connect(new InetSocketAddress("localhost", 18899));

        socketChannel.configureBlocking(false);

        System.out.println("client 成功连接服务器");

        String fileName = getFileName(file);

        while (socketChannel.finishConnect()) {
            //发送文件名称和长度
            ByteBuffer buffer = sendFileNameAndLength(fileName, file, socketChannel);
            //发送文件内容
//            int length = sendContent(file, fileChannel, socketChannel, buffer);
            int length =-1;
            if (length == -1) {
                IOUtils.closeQuietly(fileChannel);
                socketChannel.shutdownOutput();
                IOUtils.closeQuietly(socketChannel);
            }
            System.out.println("文件传输成功");
        }
    }

    private int sendContent(File file, FileChannel fileChannel, SocketChannel socketChannel, ByteBuffer buffer) throws IOException {
        System.out.println("开始传输文件");
        int length = 0;
        long progress = 0;
        while ((length = fileChannel.read(buffer)) > 0) {
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();
            progress += length;
            System.out.println("|" + (100 * progress / file.length()) + "% |");
        }
        return length;
    }

    private ByteBuffer sendFileNameAndLength(String fileName, File file, SocketChannel socketChannel) throws IOException {
        //发送文件名称
        final ByteBuffer fileNameBuffer = charset.encode(fileName);
        final ByteBuffer buffer = ByteBuffer.allocate(1024);
        final int fileNameLength = fileNameBuffer.capacity();

        //发送文件名称长度
        buffer.putInt(fileNameLength);
        buffer.flip();
        socketChannel.write(buffer);
        buffer.clear();
        System.out.println("文件名称长度 fileLength = [" + fileNameLength + "] 发送完成");

        socketChannel.write(fileNameBuffer);
        System.out.println("文件名称 fileName = [" + fileName + "] 发送完成");

        buffer.putLong(file.length());
        buffer.flip();
        socketChannel.write(buffer);
        buffer.clear();
        System.out.println("文件长度 fileLength = [" + file.length() + "] 发送完成");
        return buffer;
    }

    private String getFileName(File srcFile) {
        return srcFile.getName();
    }

    public static void main(String[] args) throws IOException {
        String scrFile ="D:\\我的照片\\许俊屹\\视频\\VID_20220705_110343.mp4";
        new NioSendClient().sendFile(scrFile);
    }
}
