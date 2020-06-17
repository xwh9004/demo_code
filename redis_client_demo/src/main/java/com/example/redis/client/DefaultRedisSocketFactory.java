package com.example.redis.client;

import java.io.IOException;
import java.net.Socket;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 11:15 on 2020/6/17
 * @version V0.1
 * @classNmae RedisSocketFactory
 */
public class DefaultRedisSocketFactory {


    public DefaultRedisSocketFactory(){

    }

    public  Socket createSocket(String host, int port) throws IOException {
        Socket socket = new Socket(host,port);
        socket.setKeepAlive(true);
       return socket;
   }
}
