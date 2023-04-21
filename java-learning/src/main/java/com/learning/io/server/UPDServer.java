package com.learning.io.server;

import com.learning.util.Printer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

/**
 * @author: xwh90
 * @date: 2023/4/21 14:44
 * @description: UPD server
 */
public class UPDServer {

    public void receive() throws IOException {
        //获取channel
        final DatagramChannel datagramChannel = DatagramChannel.open();
        //设置非阻塞
        datagramChannel.configureBlocking(false);
        //监听端口
        datagramChannel.bind(new InetSocketAddress("localhost",18899));

        Printer.info("UPD服务器启动成功");

        Selector selector =Selector.open();
        //将通道注册到selector中，监听READ事件
        datagramChannel.register(selector, SelectionKey.OP_READ);

        while (selector.select()>0){
            final Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while (keyIterator.hasNext()){
                final SelectionKey selectionKey = keyIterator.next();
                if(selectionKey.isReadable()){
                    final SocketAddress client = datagramChannel.receive(buffer);
                    buffer.flip();
                    Printer.info(new String(buffer.array()),0,buffer.limit());
                    buffer.clear();
                }
                keyIterator.remove();
            }
        }
        selector.close();
        datagramChannel.close();
    }
    public static void main(String[] args) throws IOException {
        new UPDServer().receive();
    }
}
