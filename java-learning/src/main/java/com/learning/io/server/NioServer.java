package com.learning.io.server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

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

    private StringBuilder message = new StringBuilder();

    private ServerSocketChannel serverSocketChannel;

    private Selector selector;

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
        serverSocketChannel = ServerSocketChannel.open();
        // 设置通道为非阻塞
        serverSocketChannel.configureBlocking(false);
        // 将该通道对应的ServerSocket绑定到port端口
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(port));


    }


    public void listener() {
        System.out.println("------server started--------");
        openSelector();
        while (true) {
            try {
                selector.select();  //如果没有事件，则一致阻塞
                handleKeys();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

    private void openSelector() {
        try {
            selector = Selector.open();
            System.out.println("------selector opened--------");
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleKeys() {
        Set<SelectionKey> selectKeys = selector.selectedKeys();
        Iterator<SelectionKey> keyIterator = selectKeys.iterator();
        //依次处理事件
        while (keyIterator.hasNext()) {
            SelectionKey key = keyIterator.next();
            try {
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
            } catch (Exception e) {
                final SelectableChannel channel = key.channel();
                if (channel instanceof SocketChannel) {
                    SocketChannel socketChannel = (SocketChannel) channel;
                    try {
                        final SocketAddress remoteAddress = socketChannel.getRemoteAddress();
                        socketChannel.close();
                        System.out.println("client " + remoteAddress + " disconnected！");
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        }

    }

    private void doWrite(SelectionKey selectionKey) throws IOException {
        //写数据给client
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
//        String ok_message= http_ok_message();
        if (message.toString().equals("file")) {
            zeroCopy(socketChannel);
        } else {
            final byte[] bytes = echoMessage();
            socketChannel.write(ByteBuffer.wrap(bytes));
            System.out.println("written to client:" + socketChannel.getRemoteAddress() + ":" + new String(bytes));
        }
        //非阻塞
        socketChannel.configureBlocking(false);

        //注册selector
        socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ);
    }

    private byte[] echoMessage() {
        return message.toString().getBytes(StandardCharsets.UTF_8);
    }

    private String http_ok_message() {
        return "HTTP/1.1 200\n" +
                "Content-Type: text/html;charset=UTF-8\n" +
                "Content-Length: 2\n" +
                "Date: Fri, 24 Jun 2022 06:15:09 GMT\n" +
                "Keep-Alive: timeout=60\n" +
                "Connection: keep-alive\n" +
                "\n" +
                "OK";
    }

    public static void zeroCopy(SocketChannel channel) {
        String path = "D:\\test\\我的照片\\许俊屹";
        String fileName = "2022060701";
        String suffix = ".jpg";
        String from = path.concat("\\").concat(fileName).concat(suffix);
        try (FileChannel fromChannel = new RandomAccessFile(from, "rw").getChannel();) {
            long count = fromChannel.size();
            System.out.println("count =" + count);
            long start = System.currentTimeMillis();
            long position = 0;
            while (position < count) {
                long length = count - position;
                long sendCt = fromChannel.transferTo(position, length, channel);
                if (sendCt == 0) {
                    break;
                }
                position = position + sendCt;
            }
            long end = System.currentTimeMillis();
            System.out.println("zero copy consume " + (end - start) + "ms");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void doRead(SelectionKey selectionKey) throws IOException {

        // 返回selectKey关联的传输通道。
        SocketChannel channel = (SocketChannel) selectionKey.channel();
        //读取服务器发送来的数据到缓冲区中
        message.setLength(0);
        //将缓冲区清空以备下次读取
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        int count = 0;
        count = channel.read(readBuffer);

        if (count > 0) {
            String receiveText = new String(readBuffer.array(), 0, count);
            message.append(receiveText);
        }
        System.out.println("read from client:" + message.toString());
        channel.register(selectionKey.selector(), SelectionKey.OP_WRITE);

    }

    private void doConnect(SelectionKey selectionKey) {

        System.out.println("client connected");
    }

    //建立连接的时候触发
    private void doAccept(SelectionKey selectionKey) throws IOException {
        System.out.println("server accepted");
        ServerSocketChannel serverChannel = (ServerSocketChannel) selectionKey.channel();
        SocketChannel socketChannel = serverChannel.accept();
        //非阻塞
        socketChannel.configureBlocking(false);
//        //注册selector
        socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ);
        System.out.println("client " + socketChannel.getRemoteAddress() + " connected successfully！");
    }
}
