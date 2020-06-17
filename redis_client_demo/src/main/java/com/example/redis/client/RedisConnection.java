package com.example.redis.client;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 17:19 on 2020/6/15
 * @version V0.1
 * @classNmae Connection
 */
@Slf4j
public class RedisConnection implements Closeable {

    private DefaultRedisSocketFactory redisSocketFactory = new DefaultRedisSocketFactory();

    private String host = "localhost";

    private int port = 6379;

    private Socket socket;

    private InputStream inputStream;

    private OutputStream outputStream;


    public RedisConnection(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public RedisConnection(int port) {
        this.port = port;
    }

    public void connect() {

        if (socket == null || socket.isClosed()) {

            try {
                socket = redisSocketFactory.createSocket(host, port);
                outputStream = socket.getOutputStream();
                inputStream = socket.getInputStream();
            } catch (IOException e) {
                log.error("unable to connect to {}:{}\\n{}", host, port, e.getCause());
            }
        }


    }

    @Override
    public void close() throws IOException {
        if (!socket.isClosed()) {
            socket.close();
        }
    }

    public void sendCommand(String command,String... args) throws UnsupportedEncodingException {
        byte[] bytes = new byte[8192];
        int count = 0;
        try {
            connect();
            bytes[count++] = RedisProtocol.ASTERISK_BYTE;
            bytes[count++] = (byte)('0'+args.length+1);
            bytes[count++] = '\r';
            bytes[count++] = '\n';
            bytes[count++] = RedisProtocol.DOLLAR_BYTE;
            bytes[count++] = (byte) ('0'+command.length());
            bytes[count++] = '\r';
            bytes[count++] = '\n';
            for(byte b:command.getBytes(RedisProtocol.CHARSET)){
                bytes[count++] = b;
            }
            bytes[count++] = '\r';
            bytes[count++] = '\n';
            for(String arg:args){
                bytes[count++] = RedisProtocol.DOLLAR_BYTE;
                bytes[count++] = (byte) ('0'+arg.length());
                bytes[count++] = '\r';
                bytes[count++] = '\n';
                for(byte b:arg.getBytes(RedisProtocol.CHARSET)){
                    bytes[count++] = b;

                }
                bytes[count++] = '\r';
                bytes[count++] = '\n';
            }
            outputStream.write(bytes,0,count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getBulkReply() throws IOException {
        byte[] buff = new byte[8192];
        int len = inputStream.read(buff);

        String response =new String(buff,0,len);

        return response;
    }


}
