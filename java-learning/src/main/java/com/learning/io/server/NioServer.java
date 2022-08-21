package com.learning.io.server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
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
        System.out.println("server OP_ACCEPT");
    }


    public void listener() {
        System.out.println("------server started--------");
        System.out.println("------waiting client to connect-------");
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
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
//        String ok_message= http_ok_message();
        if (message.toString().equals("file")) {
            zeroCopy(socketChannel);
        } else {
            socketChannel.write(ByteBuffer.wrap(echoMessage()));
        }
        //非阻塞
        socketChannel.configureBlocking(false);

        //注册selector
        socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ);
        System.out.println("server OP_READ");
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

        // 返回为之创建此键的通道。
        SocketChannel channel = (SocketChannel) selectionKey.channel();
        //读取服务器发送来的数据到缓冲区中
        message.setLength(0);
        //将缓冲区清空以备下次读取
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        int count = channel.read(readBuffer);
        if (count > 0) {
            String receiveText = new String(readBuffer.array(), 0, count);
            message.append(receiveText);
        }
        System.out.println("from client:" + message.toString());
        channel.register(selector, SelectionKey.OP_WRITE);
        System.out.println("server OP_WRITE");

    }

    private void doConnect(SelectionKey selectionKey) {
        System.out.println("----server doConnect----");
    }

    //建立连接的时候触发
    private void doAccept(SelectionKey selectionKey) throws IOException {
        System.out.println("server doAccept");
        SocketChannel socketChannel = ((ServerSocketChannel) selectionKey.channel()).accept();
        //非阻塞
        socketChannel.configureBlocking(false);
//        //注册selector
        socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ);
        System.out.println("server OP_READ");
        System.out.println("client " + socketChannel.getLocalAddress() + " connected successfully！");
    }
}
