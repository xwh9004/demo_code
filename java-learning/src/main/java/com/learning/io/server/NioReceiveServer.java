package com.learning.io.server;

import com.learning.util.Printer;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author: xwh90
 * @date: 2023/4/21 15:02
 * @description:
 */
public class NioReceiveServer {

    static class Client {
        String fileName;
        long fileLength;
        long startTime;
        InetSocketAddress remoteAddress;
        FileChannel fileChannel;
        long receiveLength;

        public boolean isFinished() {
            return receiveLength >= fileLength;
        }

    }

    private Charset charset = Charset.forName("UTF-8");
    public static final String RECEIVE_PATH = "D:\\我的项目\\test\\";
    private ByteBuffer buffer = ByteBuffer.allocate(1024);
    //使用Map保存美俄客户端传输，当OP_READ通道可读时，根据channel查找到对应的对象
    private Map<SelectableChannel, Client> clientMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        new NioReceiveServer().startServer();
    }

    private void startServer() throws IOException {
        final Selector selector = Selector.open();

        final ServerSocketChannel serverChannel = ServerSocketChannel.open();

        final ServerSocket serverSocket = serverChannel.socket();

        serverChannel.configureBlocking(false);

        serverSocket.bind(new InetSocketAddress("localhost", 18899));

        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        Printer.info("serverChannel is listening...");

        while (selector.select() > 0) {
            final Set<SelectionKey> selectionKeys = selector.selectedKeys();
            final Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                final SelectionKey selectionKey = keyIterator.next();
                if (selectionKey.isAcceptable()) {

                    final ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                    final SocketChannel socketChannel = server.accept();
                    if (socketChannel == null) continue;
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);

                    Client client = new Client();
                    client.remoteAddress = (InetSocketAddress) socketChannel.getRemoteAddress();
                    clientMap.put(socketChannel, client);
                    Printer.info("连接成功...");
                } else if (selectionKey.isReadable()) {
                    processData(selectionKey);
                }
                keyIterator.remove();
            }
        }
    }

    private void processData(SelectionKey selectionKey) {
        final SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        final Client client = clientMap.get(socketChannel);
        int num = 0;
        try {
            buffer.clear();
            //此处容易出现半包，粘包现象
            while ((num = socketChannel.read(buffer)) > 0) {
                buffer.flip();
                if (client.fileName == null) {
                    //int byte的长度，说明是文件名
                    if (buffer.capacity() < 4) {
                        continue;
                    }
                    final int fileNameLength = buffer.getInt();
                    byte[] fileNameBytes = new byte[fileNameLength];
                    buffer.get(fileNameBytes);

                    String fileName = new String(fileNameBytes, charset);
                    client.fileName = fileName;

                    final File directory = new File(RECEIVE_PATH);
                    if (!directory.exists()) {
                        directory.mkdir();
                    }
                    Printer.info("NIO 传输目标路径：%s", directory.getAbsolutePath());
                    String fullName = directory.getAbsolutePath() + File.separatorChar + fileName;
                    Printer.info("NIO 传输目标文件：%s", fullName);
                    File file = new File(fullName.trim());
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileChannel fileChannel = new FileOutputStream(file).getChannel();
                    client.fileChannel = fileChannel;
                    //???
                    if (buffer.capacity() < 8) {
                        continue;
                    }
                    long fileLength = buffer.getLong();
                    client.fileLength = fileLength;
                    client.startTime = System.currentTimeMillis();
                    Printer.info("NIO 开始传输 预计接收长度 %s：",fileLength);
                    client.receiveLength += buffer.capacity();
                    if (buffer.capacity() > 0) {
                        client.fileChannel.write(buffer);
                    }
                    if (client.isFinished()) {
                        finished(selectionKey, client);
                    }
                    buffer.clear();
                } else {
                    client.receiveLength += buffer.capacity();
                    if (buffer.capacity() > 0) {
                        client.fileChannel.write(buffer);
                    }
                    if (client.isFinished()) {
                        finished(selectionKey, client);
                    }
                    buffer.clear();
                }
                selectionKey.cancel();
            }
        } catch (IOException e) {
            selectionKey.cancel();
            return;
        }

        if (num == -1) {
            finished(selectionKey, client);
            buffer.clear();
        }
    }

    private void finished(SelectionKey selectionKey, Client client) {
        IOUtils.closeQuietly(client.fileChannel);
        Printer.info("上传完毕");
        selectionKey.cancel();
        Printer.info("文件接收成功，File Name：" + client.fileName);
        Printer.info("Size：" + client.fileLength);
        long endTime = System.currentTimeMillis();
        Printer.info("NIO IO 传输毫秒数：" + (endTime -client.startTime));
    }

}
