package com.example.redis.client;

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

    private static final String SEPARATOR = "\r\n";


    private RedisConnection connection;


    public RedisClient(String host, int port) {
        connection = new RedisConnection(host, port);
    }

//    public String get(String key) {
//        String command = RedisCommand.build(RedisCommand.GET, key);
//        String response = connection.sendCommand(command);
//        return (String)parseResp(response);
//    }
//
//
//    public Set<String> keys(String pattern) {
//        String command = RedisCommand.build(RedisCommand.KEYS, pattern);
//        String response = connection.sendCommand(command);
//        return (Set<String>)parseResp(response);
//    }
//
//
//
//    /**
//     * 这是key-value 键值对
//     *
//     * @param key
//     * @param value
//     * @return
//     */
//    public String set(String key, String value) {
//        String command = RedisCommand.build(RedisCommand.SET, value);
//        String response = connection.sendCommand(command);
//        return (String)parseResp(response);
//    }
//
//    ;
//


    private Object parseResp(String response) {
        char prefix = response.charAt(0);
        Object result = null;
        switch (prefix) {
            case '+':
                result = parseSimpleString(response);
                break;
            case '-':
                result = parseErrorString(response);
                break;
            case ':':
                result = parseIntegers(response);
                break;
            case '$':
                result = parseBulkString(response);
                break;
            case '*':
                result = parseArraysString(response);
                break;

        }
        //去除最后的一个换行

        return result;
    }

    /**
     * 解析 redis-server返回的字符串数组
     *
     * @param resp
     * @return
     */
    private Set<String> parseArraysString(String resp) {
        Set<String> result = new HashSet<>();
        char prefix = resp.charAt(0);
        if ('*' == prefix) {
            String[] split = resp.split(SEPARATOR);
            //返回数组的大小
            int len = Integer.valueOf(split[0].replace("*", ""));
            if (len > 0) {
                for (int i = 2; i < split.length; i += 2) {
                    result.add(split[i]);
                }
            }
        }
        return result;
    }

    /**
     * 解析 redis-server返回的字符串信息
     *
     * @param resp
     * @return
     */
    private String parseBulkString(String resp) {
        String result = null;
        char prefix = resp.charAt(0);
        if (prefix == '$') {
            /**
             * 结果解析需要增加校验
             */
            String res = resp.substring(1);
            String[] split = res.split(SEPARATOR);
            int len = Integer.valueOf(split[0]);
            result = split[1];
        }

        return result;
    }

    /**
     * 解析 redis-server返回的错误信息
     *
     * @param resp
     * @return
     */
    private String parseIntegers(String resp) {
        String result = null;
//        result = resp.substring(0,resp.length()-2);
        return result;
    }

    /**
     * 解析 redis-server返回的错误信息
     *
     * @param resp
     * @return
     */
    private String parseErrorString(String resp) {
        String result = null;
        result = resp.substring(1, resp.length() - 2);
        return result;
    }

    /**
     * 解析redis server 返回的简单字符串
     *
     * @param resp
     * @return
     */
    private String parseSimpleString(String resp) {
        String result = null;
        result = resp.substring(1, resp.length() - 2);
        return result;
    }

    @Override
    public String ping(String message) {
        String response = null;
        try {
            connection.sendCommand(RedisProtocol.Command.PING,message);

            response =connection.getBulkReply();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public String get(String key) {
        return null;
    }

    @Override
    public String clientList() {
        String response = null;
        try {
            connection.sendCommand(RedisProtocol.Command.CLIENT,"list");
            response =connection.getBulkReply();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
