package com.demo.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 17:10 on 2020/5/14
 * @version V0.1
 * @classNmae ChannelClient
 */
public class ChannelClient {

    public static void main(String[] args) throws IOException {
        SocketChannel client = SocketChannel.open();
        client.connect(new InetSocketAddress("127.0.0.1",8080));
        System.out.println("准备连接服务器");
        DataInputStream dis = null;
        DataOutputStream dout = null;
        dout = new DataOutputStream(client.socket().getOutputStream());
        dis = new DataInputStream(client.socket().getInputStream());
        Scanner scanner = new Scanner(System.in);
        while (true) {
            //发送数据
            dout.writeUTF(scanner.nextLine());
            //接收数据
            System.out.println("server:" + dis.readUTF());
        }

    }
}
