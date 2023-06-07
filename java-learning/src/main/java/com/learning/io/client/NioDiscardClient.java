package com.learning.io.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author: xwh90
 * @date: 2023/4/21 9:35
 * @description:
 */
public class NioDiscardClient {

    public static void main(String[] args) throws IOException {
       startClient();
    }

    public static void startClient()throws IOException {
        InetSocketAddress address = new InetSocketAddress("127.0.0.1",18899);
        //获取通道
        final SocketChannel socketChannel = SocketChannel.open(address);
        //设置非阻塞
        socketChannel.configureBlocking(false);
        while (!socketChannel.finishConnect());

        System.out.println("客户端连接完成");

        final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("hello server".getBytes(StandardCharsets.UTF_8));
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        socketChannel.shutdownOutput();
        socketChannel.close();
    }
}
