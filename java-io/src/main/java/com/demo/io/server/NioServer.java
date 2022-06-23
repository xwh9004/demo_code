package com.demo.io.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 16:52 on 2020/5/14
 * @version V0.1
 * @classNmae NioServer
 */
public class NioServer {

    private Selector selector;

    private StringBuilder message = new StringBuilder();

     private  ByteBuffer buffer = ByteBuffer.allocate(1024);

    public static void main(String[] args) throws IOException {
        NioServer server = new NioServer();
        server.initServer(8090);
        server.listener();
    }

    private void acceptRequest(SelectionKey selectionKey) throws IOException {
        //获取channel
        SocketChannel socketChannel = ((ServerSocketChannel) selectionKey.channel()).accept();
        //非阻塞
        socketChannel.configureBlocking(false);
        //注册selector
        socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(1024));

        System.out.println("建立请求......");

    }

    private String doRequest(StringBuilder req) {


        System.out.println("开始处理请求 request = " + req);

        String response = req.reverse().toString();

        System.out.println("处理请求结束");
        return response;
    }

    private void initServer(int port) throws IOException {

        // 获得一个ServerSocketChannel通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 设置通道为非阻塞
        serverSocketChannel.configureBlocking(false);
        // 将该通道对应的ServerSocket绑定到port端口
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(port));

        selector = Selector.open();

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);


    }


    public void listener() {
        System.out.println("服务端启动成功");
        try {
            while (true) {
                selector.select();  //如果没有事件，则一致阻塞
                Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                //依次处理事件
                while (keyIterator.hasNext()) {

                    SelectionKey key = keyIterator.next();

                    if (key.isConnectable()) {
                        doConnect(key);
                    }
                    // 客户端请求连接事件
                    if (key.isAcceptable()) {
                        doAccept(key);
                    }
                    if (key.isReadable()) {
                        doRead(key);
                    }
                    if (key.isWritable()) {
                        doWrite(key);
                    }
                    //处理完后移除当前使用的key
                    keyIterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doWrite(SelectionKey selectionKey) throws IOException {
        //写数据给client

        SocketChannel client = (SocketChannel) selectionKey.channel();
        client.write(ByteBuffer.wrap(new String(message.toString()).getBytes("UTF-8")));
        //注册selector
        client.register(selectionKey.selector(), SelectionKey.OP_READ);
    }

    private void doRead(SelectionKey selectionKey) throws IOException {
        SocketChannel client = (SocketChannel) selectionKey.channel();
        // 返回为之创建此键的通道。
        client = (SocketChannel) selectionKey.channel();
        //将缓冲区清空以备下次读取
        buffer.clear();
        //读取服务器发送来的数据到缓冲区中
        message.setLength(0);
        int count = client.read(buffer);
        if (count > 0) {
            String receiveText = new String(buffer.array(), 0, count);
            message.append(receiveText);

        }
        System.out.println("from client:" + message.toString());
        client.register(selector, SelectionKey.OP_WRITE);
    }

    private void doConnect(SelectionKey selectionKey) {
        System.out.println("server doConnect");
    }

    //建立连接的时候触发
    private void doAccept(SelectionKey selectionKey) throws IOException {

        SocketChannel socketChannel = ((ServerSocketChannel) selectionKey.channel()).accept();
        //非阻塞
        socketChannel.configureBlocking(false);

        socketChannel.write(ByteBuffer.wrap(new String("连接成功！").getBytes("UTF-8")));

        //注册selector
        socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ);
        System.out.println("客户端" + socketChannel.getLocalAddress() + "链接成功！");
    }
}
