package com.example.redis.client.exception;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 9:21 on 2020/6/19
 * @version V0.1
 * @classNmae RedisException
 */
public class RedisException extends RuntimeException {

    public RedisException(String msg){
        super(msg);
    }

}
