package com.demo.io.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 17:10 on 2020/5/14
 * @version V0.1
 * @classNmae NioClient
 */
public class NioClient {

    Selector selector;

    SocketChannel client;

    ByteBuffer buffer = ByteBuffer.allocate(1024);

    public static void main(String[] args) throws IOException {
        NioClient client = new NioClient();
        client.initClient(8090);
        client.listener();
    }

    public void initClient(int port) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        // 设置为非阻塞方式
        socketChannel.configureBlocking(false);
        // 打开选择器
        selector = Selector.open();
        // 注册连接服务端socket动作
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(new InetSocketAddress(8090));
        System.out.println("准备连接服务器");
    }

    public void listener() throws IOException {
        System.out.println("开始监听服务端数据");
        SelectionKey selectionKey = null;
        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            //依次处理事件
            while (iterator.hasNext()) {
                selectionKey = iterator.next();
                if (selectionKey.isConnectable()) {
                    doConnect(selectionKey);
                }
                if (selectionKey.isAcceptable()) {
                    doAccept(selectionKey);
                }
                if (selectionKey.isReadable()) {
                    doRead(selectionKey);
                }
                if (selectionKey.isWritable()) {
                    doWrite(selectionKey);
                }
            }
        }

    }

    private void doConnect(SelectionKey selectionKey) throws IOException {

        System.out.println("client connect");

        client = (SocketChannel) selectionKey.channel();
        // 判断此通道上是否正在进行连接操作。
        // 完成套接字通道的连接过程。
        buffer.clear();
        if (client.isConnectionPending()) {
            client.finishConnect();
            int len = client.read(buffer);
            while(len>0){
                String  receiveText = new String(buffer.array(), 0, len);
                System.out.println("from server --:" + receiveText);
                len = client.read(buffer);
            }
            System.out.println("server connected!");
            client.write(ByteBuffer.wrap(new String("Hello Server!").getBytes("UTF-8")));
        }
        client.register(selector, SelectionKey.OP_READ);
    }

    //在服务端有效
    private void doAccept(SelectionKey selectionKey) throws IOException {
        System.out.println("client doAccept");
    }

    private void doRead(SelectionKey selectionKey) throws IOException {
        SocketChannel server = (SocketChannel) selectionKey.channel();

        // 返回为之创建此键的通道。
        server = (SocketChannel) selectionKey.channel();
        //将缓冲区清空以备下次读取
        buffer.clear();
        //读取服务器发送来的数据到缓冲区中
        StringBuilder sb =  new StringBuilder();
        int count = server.read(buffer);
        while (count > 0) {
            String  receiveText = new String(buffer.array(), 0, count);
            sb.append(receiveText);
            count = server.read(buffer);
        }
        System.out.println("from server:" + sb.toString());
        server.register(selector, SelectionKey.OP_WRITE);
    }

    private void doWrite(SelectionKey selectionKey) throws IOException {
        //写数据给client
        Scanner scanner =new Scanner(System.in);
        System.out.println("client send:");
        String message =scanner.nextLine();
        client = (SocketChannel) selectionKey.channel();
        buffer.clear();
        buffer.put(new String(message).getBytes("UTF-8"));
        client.write(buffer);
        //注册selector
        client.register(selectionKey.selector(), SelectionKey.OP_READ);
    }
}
