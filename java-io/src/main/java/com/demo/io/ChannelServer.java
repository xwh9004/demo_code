package com.demo.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 16:52 on 2020/5/14
 * @version V0.1
 * @classNmae ChannelServer
 */
public class ChannelServer {

    public static void main(String[] args) {
        //try-with-resource
        //打开channel
        try(ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()){
            //socket binding port
            serverSocketChannel.socket().bind(new InetSocketAddress(8080));
            //设置为非阻塞模式
            serverSocketChannel.configureBlocking(false);
            //为serverChannel注册selector
            Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("服务端开始工作：");
            while(true){
                selector.select();
                Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                System.out.println("开始处理请求 ： ");
                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    try {
                        //连接请求
                        if (key.isAcceptable()) {
                            acceptRequest(key);
                        }
                        //读请求
                        if (key.isReadable()) {
                         handlerRequest(key);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //处理完后移除当前使用的key
                    keyIterator.remove();
                }
                System.out.println("完成请求处理。");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void acceptRequest(SelectionKey selectionKey) throws IOException {
        //获取channel
        SocketChannel socketChannel = ((ServerSocketChannel) selectionKey.channel()).accept();
        //非阻塞
        socketChannel.configureBlocking(false);
        //注册selector
        socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(1024));

        System.out.println("建立请求......");

    }
    private static void handlerRequest(SelectionKey selectionKey) throws IOException {

        SocketChannel sc = (SocketChannel)selectionKey.channel();
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        int readBytes = sc.read(readBuffer);
        if(readBytes > 0 ) {
            readBuffer.flip();//将缓冲区当前的limit设置为position,position设置为0
            byte[] bytes = new byte[readBuffer.remaining()];
            readBuffer.get(bytes);
            String body = new String(bytes, "UTF-8");
            String response = processRequest(body);
            byte[] resBytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(resBytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            sc.write(writeBuffer);
        }


    }

    private static String processRequest(String  req) {


        System.out.println("开始处理请求 request = " + req);

        String response = req.toUpperCase();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("处理请求结束");
        return response;
    }
}
