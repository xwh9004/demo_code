package com.example.redis.client;

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

    private static String getBulkReply(RedisInputStream inputStream) throws IOException {
        int len =inputStream.readIntCLRF();
        byte[] b = new byte[len];
        int length =inputStream.read(b,0,len);
        //read 2 more bytes for delimiter
        inputStream.readByte();
        inputStream.readByte();
        return new String(b);
//        return inputStream.readLine();
    }

    private static List getArrayReply(RedisInputStream inputStream) {

        return null;
    }

    private static String getErrorReply(RedisInputStream inputStream) {
        return null;
    }

    private static String getSimpleReply(RedisInputStream inputStream) {
        return null;
    }

    private static Integer getIntegerReply(RedisInputStream inputStream) {
        return null;
    }

    public static Object getRedisReply(RedisInputStream inputStream) throws IOException {
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
        PING, CLIENT;

        byte[] getRaw() throws UnsupportedEncodingException {
            return name().getBytes(CHARSET);
        }
    }


}
