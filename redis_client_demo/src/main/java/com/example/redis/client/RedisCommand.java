package com.example.redis.client;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 14:14 on 2020/6/16
 * @version V0.1
 * @classNmae RedisCommand
 */
public interface RedisCommand {

    String ping(final String message);

    String get(final String key);

    String set(final String key,final String value);

    String clientList();

    long incr(String key);





}
