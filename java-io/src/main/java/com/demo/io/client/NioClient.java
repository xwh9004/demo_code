package com.demo.io.client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
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

        client = (SocketChannel) selectionKey.channel();
        // 判断此通道上是否正在进行连接操作。
        // 完成套接字通道的连接过程。
        if (client.isConnectionPending()) {
            System.out.println("server connecting...");
            client.finishConnect();
            System.out.println("server connected!");
            client.register(selector, SelectionKey.OP_WRITE);
            System.out.println("client  OP_WRITE");
        }
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
        ByteBuffer buffer = ByteBuffer.allocate(1024*1024);
        //读取服务器发送来的数据到缓冲区中
        StringBuilder sb = new StringBuilder();
//        int count = server.read(buffer);
        long count =0;
         count = zeroCopy(server,count,1024*1024);
        long total = count;
        while (count > 0) {
//            String receiveText = new String(buffer.array(), 0, count);
//            sb.append(receiveText);

//            count = server.read(buffer);
            count =zeroCopy(server,total,1024*1024);
            total += count;
            System.out.println("from server bytes total =" + total);

        }
        System.out.println("from server:" + sb.toString());
        server.register(selector, SelectionKey.OP_WRITE);
        System.out.println("client  OP_WRITE");
    }
    public static long zeroCopy(SocketChannel channel,long off,int size) {
        String path = "D:\\test\\我的照片\\许俊屹";
        String fileName = "2022060701";
        String suffix = "copy.jpg";
        long count =-1;
        String to = path.concat("\\").concat(fileName).concat(suffix);
        try (FileChannel fileChannel = new RandomAccessFile(to, "rw").getChannel();) {
            count =fileChannel.transferFrom(channel,off,size);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return count;
    }

    private void doWrite(SelectionKey selectionKey) throws IOException {
        //写数据给client
        Scanner scanner = new Scanner(System.in, "UTF-8");
        System.out.println("client send:");
        String message = scanner.nextLine();
        client = (SocketChannel) selectionKey.channel();
        byte[] bytes = message.getBytes("UTF-8");
        client.write(ByteBuffer.wrap(bytes));
        //注册selector
        client.register(selectionKey.selector(), SelectionKey.OP_READ);
        System.out.println("client  OP_READ");
    }
}
