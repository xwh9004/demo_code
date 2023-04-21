package com.learning.io.client;

import com.learning.util.DataTimeUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * @author: xwh90
 * @date: 2023/4/21 14:11
 * @description: UPD
 */
public class UPDClient {
    public void send() throws IOException {
        //获取datagramChannel
        final DatagramChannel datagramChannel = DatagramChannel.open();
        //设置非阻塞
        datagramChannel.configureBlocking(false);
        //
        final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        final Scanner scanner = new Scanner(System.in);

        System.out.println("UDP客户端启动成功");

        System.out.println("请输入发送内容：");

        while (scanner.hasNext()){
            final String line = scanner.nextLine();
            final byte[] bytes = (DataTimeUtil.now() + " >>" + line).getBytes();
            byteBuffer.put(bytes);
            byteBuffer.flip();
            datagramChannel.send(byteBuffer,new InetSocketAddress("localhost",18899));
            byteBuffer.clear();
        }
        datagramChannel.close();
    }

    public static void main(String[] args) throws IOException {
        new UPDClient().send();
    }
}
