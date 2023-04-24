package com.learning.io.reactor;

import com.learning.util.Printer;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author: xwh90
 * @date: 2023/4/24 9:59
 * @description: 代码摘抄于 高并发核心编程 第四章第二节 详解单线程Reactor的参考示例
 */
public class EchoServerReactor implements Runnable {

    Selector selector;

    ServerSocketChannel serverSocket;

    EchoServerReactor() throws IOException {
        //打开连接通道
        serverSocket = ServerSocketChannel.open();
        serverSocket.configureBlocking(false);

        serverSocket.bind(new InetSocketAddress("localhost",18899));
        //打开选择器
        selector = Selector.open();

        //注册serverSocket的accept事件
        final SelectionKey selectionKey = serverSocket.register(this.selector, SelectionKey.OP_ACCEPT);

        selectionKey.attach(new AcceptorHandler());

        Printer.info("echo 服务器启动成功");
    }

    @SneakyThrows
    @Override
    public void run() {
        while (!Thread.interrupted()) {
            selector.select();
            final Set<SelectionKey> selectionKeys = selector.selectedKeys();
            final Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                final SelectionKey selectionKey = keyIterator.next();
                dispatch(selectionKey);
                keyIterator.remove();
            }
            selectionKeys.clear();
        }
    }

    private void dispatch(SelectionKey selectionKey) {
        final Runnable handler = (Runnable) selectionKey.attachment();
        if (handler != null) {
            //此处为什么用run而不用start? 因为是单线程
            handler.run();
        }
    }

    class AcceptorHandler implements Runnable {

        @Override
        public void run() {
            try {
                //接收客户端连接
                final SocketChannel sockChanel = serverSocket.accept();

                if (sockChanel != null) {
                    new EchoHandler(selector, sockChanel);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    class EchoHandler implements Runnable {
        Selector selector;
        SocketChannel socketChannel;
        static final int RECEIVING = 0;
        static final int SENDING = 1;
        int state = RECEIVING;
        final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        final SelectionKey selectionKey;

        EchoHandler(Selector selector, SocketChannel socketChannel) throws IOException {
            this.selector = selector;
            this.socketChannel = socketChannel;
            this.socketChannel.configureBlocking(false);
            //原因是 将EchoHandler实体注入到selectionKey中,以便在selector中选择,dispatch中处理
            selectionKey = socketChannel.register(selector, 0);
            selectionKey.attach(this);
            selectionKey.interestOps(SelectionKey.OP_READ);
            selector.wakeup();
        }

        @Override
        public void run() {
            try {
                //接收客户端连接
                if (state == SENDING) {
                    socketChannel.write(byteBuffer);
                    byteBuffer.clear();
                    selectionKey.interestOps(SelectionKey.OP_READ);
                    state = RECEIVING;
                } else if (state == RECEIVING) {
                    int length = 0;
                    while ((length = socketChannel.read(byteBuffer)) > 0) {
                        Printer.info(new String(byteBuffer.array(),0,length));
                    }
                    byteBuffer.flip();
                    selectionKey.interestOps(SelectionKey.OP_WRITE);
                    state = SENDING;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Thread(new EchoServerReactor()).start();
    }
}
