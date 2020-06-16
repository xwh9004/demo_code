package com.example.redis.client;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.io.IOException;
import java.io.InputStream;
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
public class RedisConnection {

    private String host = "localhost";

    private int port = 6379;

    private Socket socket;


    public RedisConnection(String host, int port) {
        this.host = host;
        this.port = port;
        connect();
    }

    public RedisConnection(int port) {
        this.port = port;
        connect();

    }

    public void connect() {
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            log.error("unable to connect to {}:{}\\n{}", host, port, e.getCause());
        }
    }

    public void close() throws IOException {
        if (!socket.isClosed()) {
            socket.close();
        }
    }

    public String sendCommand(String command) {
        byte[] outBytes =command.getBytes(StandardCharsets.UTF_8);
        int buffSize =1024;
        byte[] buffer = new byte[buffSize];
        StringBuilder response = new StringBuilder();
        int len = 0;
        try {
            socket.getOutputStream().write(outBytes);
           InputStream in =socket.getInputStream();
            do{
                len =in.read(buffer);
                response.append(new String(buffer, 0 , len, StandardCharsets.UTF_8));
            }while(len!=-1&&len==buffSize);
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
