package com.example.redis.client;

import com.example.redis.client.exception.RedisException;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

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

    private int position;

    private int limit;


    public RedisInputStream(InputStream in) {
        super(in);
        buff = new byte[8192];
    }


    public String readLine() throws RedisException {
        fillBuff();
        StringBuilder sb = new StringBuilder();
        while (true) {
            byte b = buff[position++];
            if (b == '\r') {
                int c = buff[position++];
                if (c == '\n') {
                    break;
                }
                sb.append((char) b);
                sb.append((char) c);
            } else {
                sb.append((char) b);
            }
        }
        return sb.toString();
    }

    @Override
    public int read(byte[] b, int off, int len) throws RedisException {
        fillBuff();
        int length = Math.min(limit - position, len);
        System.arraycopy(buff, position, b, 0, length);
        position = position + length;
        return length;

    }

    public int readIntCLRF() throws RedisException {
        return (int) readLongCLRF();
    }

    public long readLongCLRF() throws RedisException {
        fillBuff();
        boolean negative = false;
        //value is negative
        if (RedisProtocol.MINUS_BYTE == buff[position++]) {
            negative = true;
        }
        position = negative == true ? position : position - 1;
        long value = 0;
        while (true) {
            fillBuff();
            byte b = buff[position++];
            if (b == '\r') {
                if (buff[position++] != '\n') {
                    throw new RedisException("Unexpected character!");
                }
                break;
            } else {
                value = value * 10 + b - '0';
            }
        }

        return negative == true ? -value : value;
    }

    public byte readByte() throws  RedisException {
        fillBuff();
        return buff[position++];
    }


    private void fillBuff() throws RedisException {
        try {
            if (position >= limit) {

                limit = in.read(buff);

                position = 0;
                if (limit == -1) {
                    throw new RedisException("Unexpected end of stream");
                }
            }
        } catch (IOException e) {
            throw new RedisException(e.getMessage());
        }
    }

}
