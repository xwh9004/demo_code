package com.learning.io.server;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @author: xwh90
 * @date: 2023/4/21 8:59
 * @description:
 */
public class NioDiscardServer {

    public static void startServer() throws IOException {
        //1获取选择器
        final Selector selector = Selector.open();
        //2获取通道
        final ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //3设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //4绑定连接
        serverSocketChannel.bind(new InetSocketAddress(18899));
        System.out.println("服务器启动成功");

        //5将通道注册的“接受新连接IO时间注册到选择器上”
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //6轮询感兴趣的IO就绪事件（选择键集合）
        while (selector.select() > 0) {
            //7获取选择键集合
            final Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
            //8 获取单个的选择键，并处理
            while (selectedKeys.hasNext()) {
                final SelectionKey selectionKey = selectedKeys.next();
                //9 判断Key是具体的什么事件
                if (selectionKey.isAcceptable()) {
                    //10 若选择键的IO事件是“连接就绪”，就获取客户端连接
                    final SocketChannel socketChannel = serverSocketChannel.accept();
                    //11 将连接设置为非阻塞模式
                    socketChannel.configureBlocking(false);
                    //12 将新连接的通道的可读事件注册到选择器上
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    //13 若选择键的IO事件是“可读”，则读取数据
                    final SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    //14 读取数据，然后打印丢弃
                    final ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int length = 0;
                    while ((length = socketChannel.read(buffer)) > 0) {
                        //翻转成可读模式
                        buffer.flip();
                        //
                        System.out.println(new String(buffer.array(),0,length));
                    }
                    //关闭连接
                    socketChannel.close();
                }
                selectedKeys.remove();
            }
            serverSocketChannel.close();
        }
    }

    public static void main(String[] args) throws IOException {
        startServer();
    }
}
