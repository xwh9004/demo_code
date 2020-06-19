package com.example.redis.client;

import com.example.redis.client.exception.RedisException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 11:06 on 2020/6/17
 * @version V0.1
 * @classNmae RedisProtocol
 */
public class RedisProtocol {

    public static final String CHARSET = "UTF-8";

    public static final byte DOLLAR_BYTE = '$';
    public static final byte ASTERISK_BYTE = '*';
    public static final byte PLUS_BYTE = '+';
    public static final byte MINUS_BYTE = '-';
    public static final byte COLON_BYTE = ':';

    private static String getBulkReply(RedisInputStream inputStream) throws RedisException {
        int len =inputStream.readIntCLRF();
        byte[] b = new byte[len];
        int length =inputStream.read(b,0,len);
        //read 2 more bytes for delimiter
        inputStream.readByte();
        inputStream.readByte();
        return new String(b);
//        return inputStream.readLine();
    }

    private static List getArrayReply(RedisInputStream inputStream) throws  RedisException {
        inputStream.readIntCLRF();

        return null;
    }

    private static String getErrorReply(RedisInputStream inputStream) throws RedisException  {
//        int len =inputStream.readIntCLRF();
        String response=inputStream.readLine();
        return response;
    }

    private static String getSimpleReply(RedisInputStream inputStream) throws RedisException {
        return null;
    }

    /**
     * return integer number ; the integer is guaranteed to be in range of 64 bytes;
     * @param inputStream
     * @return
     */
    private static Long getIntegerReply(RedisInputStream inputStream) throws RedisException {
        long l =inputStream.readLongCLRF();
        return Long.valueOf(l);
    }

    public static Object getRedisReply(RedisInputStream inputStream) throws RedisException {
        byte prefix = inputStream.readByte();
        if (DOLLAR_BYTE == prefix) {
            return getBulkReply(inputStream);
        } else if (ASTERISK_BYTE == prefix) {
            return getArrayReply(inputStream);
        } else if (PLUS_BYTE == prefix) {
            return getSimpleReply(inputStream);
        } else if (COLON_BYTE == prefix) {
            return getIntegerReply(inputStream);
        } else if (MINUS_BYTE == prefix) {
            return getErrorReply(inputStream);
        }
        return null;
    }

    public static enum Command {
        PING, CLIENT,GET,INCR;

        byte[] getRaw() throws UnsupportedEncodingException {
            return name().getBytes(CHARSET);
        }
    }


}
