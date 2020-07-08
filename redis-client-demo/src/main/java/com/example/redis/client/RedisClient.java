package com.example.redis.client;

import com.example.redis.client.exception.RedisException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 17:34 on 2020/6/15
 * @version V0.1
 * @classNmae RedisClient
 */
@Slf4j
public class RedisClient implements RedisCommand {


    private RedisConnection connection;


    public RedisClient(String host, int port) {
        connection = new RedisConnection(host, port);
    }


    @Override
    public String ping(String message) {
        String response = null;
        try {
            connection.sendCommand(RedisProtocol.Command.PING,message);
            response =connection.getBulkReply();

        } catch (RedisException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public String get(String key)  {
        String response =null;
        try {
            connection.sendCommand(RedisProtocol.Command.GET,key);
            response=connection.getBulkReply();
        } catch (RedisException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public String set(String key, String value) {
        return null;
    }

    @Override
    public String clientList()  {
        String response = null;
        try {
            connection.sendCommand(RedisProtocol.Command.CLIENT,"list*");
            response =connection.getBulkReply();
        } catch (RedisException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public long incr(String key) {
        Long response = null;
        try {
            connection.sendCommand(RedisProtocol.Command.INCR,key);
            response = connection.getLongReply();
        } catch (RedisException e) {
            e.printStackTrace();
        }
        return response.longValue();
    }
}
