package com.example.redis.client;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.OutputStream;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 16:28 on 2020/6/17
 * @version V0.1
 * @classNmae RedisOutputStream
 */
public class RedisOutputStream extends FilterOutputStream {

    private byte[] buff;


    protected int count =0;


    RedisOutputStream(OutputStream out){
        super(out);
    }


    public void write(byte b){
        if(count==buff.length){

        }
        buff[count++] = b;
    }

    public void write(byte[] bytes){

    }
}
