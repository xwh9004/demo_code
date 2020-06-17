package com.example.redis.client;

import java.io.FilterInputStream;
import java.io.InputStream;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 16:28 on 2020/6/17
 * @version V0.1
 * @classNmae RedisInputStream
 */
public class RedisInputStream extends FilterInputStream {

    private byte[] buff;

    private int size;


    RedisInputStream(InputStream in) {
        super(in);
    }


}
