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

    private RedisInputStream inputStream;

    private RedisOutputStream outputStream;


    public RedisConnection(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public RedisConnection(int port) {
        this.port = port;
    }

    public void connect() throws IOException {

        if (socket == null || socket.isClosed()) {

            try {
                socket = redisSocketFactory.createSocket(host, port);
                outputStream = new RedisOutputStream(socket.getOutputStream());
                inputStream = new RedisInputStream(socket.getInputStream());
            } catch (IOException e) {
                log.error("unable to connect to {}:{} \n {}", host, port, e.getCause());
                throw e;
            }
        }


    }

    @Override
    public void close() throws IOException {
        if (!socket.isClosed()) {
            socket.close();
        }
    }

    public void sendCommand(RedisProtocol.Command command, String... args) throws UnsupportedEncodingException {

        try {
            connect();
            outputStream.write(RedisProtocol.ASTERISK_BYTE);
            outputStream.writeIntCRLF(args.length+1);
            outputStream.write(RedisProtocol.DOLLAR_BYTE);
            outputStream.writeIntCRLF(command.name().length());
            outputStream.write(command.getRaw());
            outputStream.writeCRLF();
            for(String arg:args){
                outputStream.write(RedisProtocol.DOLLAR_BYTE);
                outputStream.writeIntCRLF(arg.length());
                outputStream.write(arg.getBytes(RedisProtocol.CHARSET));
                outputStream.writeCRLF();
            }
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getBulkReply() throws IOException {

        String response = (String)RedisProtocol.getRedisReply(inputStream);



        return response;
    }


}
