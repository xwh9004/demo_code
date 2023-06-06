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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: xwh90
 * @date: 2023/4/24 15:39
 * @description:
 */
public class MultiThreadEchoServerReactor {
    ServerSocketChannel serverSocket;
    AtomicInteger next = new AtomicInteger(0);

    //选择器集合，引入多个选择器
    Selector[] selectors = new Selector[2];

    SubReactor[] subReactors ;

    MultiThreadEchoServerReactor() throws IOException {
        selectors[0] = Selector.open();
        selectors[1] = Selector.open();

        serverSocket = ServerSocketChannel.open();

        serverSocket.bind(new InetSocketAddress("localhost", 18890));

        serverSocket.configureBlocking(false);
        //第一个选择器selector[0],负责监控新的连接事件
        final SelectionKey selectionKey = serverSocket.register(selectors[0], SelectionKey.OP_ACCEPT);

        selectionKey.attach(new AcceptorHandler());
        //第一个选子反应器,负责第一个选择器的新连接事件的分发
        final SubReactor subReactor1 = new SubReactor(selectors[0]);
        //第二个选子反应器,负责第二个选择器的传输事件的分发（尔不处理）
        final SubReactor subReactor2 = new SubReactor(selectors[1]);
        subReactors =new SubReactor[]{subReactor1,subReactor2};

    }

    public void startServer() {
        new Thread(subReactors[0]).start();
        new Thread(subReactors[1]).start();
    }

    public static void main(String[] args) throws IOException {
        final MultiThreadEchoServerReactor serverReactor = new MultiThreadEchoServerReactor();
        serverReactor.startServer();
        Printer.info("MultiThreadEchoServerReactor 服务启动成功");
        Printer.info("等待连接...");
    }

    class SubReactor implements Runnable {
        //每个线程负责一个选择器的查询
        final Selector selector;

        public SubReactor(Selector selector) {
            this.selector = selector;
        }

        @Override
        public void run() {
            try {
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

            } catch (IOException e) {
                e.printStackTrace();
            }
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
                    new MultiEchoHandler(selectors[1], sockChanel);
                    Printer.info("客户端连接成功[ip: %s]", sockChanel.getRemoteAddress().toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    static class MultiEchoHandler implements Runnable {
        private SocketChannel socketChannel;
        private static final int RECEIVING = 0;
        private static final int SENDING = 1;
        private int state = RECEIVING;
        private final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        private final SelectionKey selectionKey;
        //线程池处理
        private static ExecutorService pool = Executors.newFixedThreadPool(4);

        MultiEchoHandler(Selector selector, SocketChannel socketChannel) throws IOException {
            this.socketChannel = socketChannel;
            this.socketChannel.configureBlocking(false);
            //原因是 将EchoHandler实体注入到selectionKey中,以便在selector中选择,dispatch中处理
            selector.wakeup();

            selectionKey = socketChannel.register(selector, 0);
            selectionKey.attach(this);
            selectionKey.interestOps(SelectionKey.OP_READ);
            selector.wakeup();
        }

        @Override
        public void run() {
            Printer.info("线程【%s】执行handler",Thread.currentThread().getName());
            pool.submit(() -> {
                asyncRun();
            });
        }

        public synchronized void asyncRun() {
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
                        Printer.info(new String(byteBuffer.array(), 0, length));
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
}
