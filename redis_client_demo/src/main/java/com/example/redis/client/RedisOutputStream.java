package com.example.redis.client;

import java.io.*;

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

    protected int count = 0;
    private byte[] buff;

    private final static int[] intSizeTable = {9,99,999,9999,99999,999999,9999999,99999999,999999999,Integer.MAX_VALUE};

    private final static int[] moduleTable ={1,10,100,1000,10000,100000,1000000,10000000,100000000,1000000000};

    private final static byte[] digitBytes ={'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    RedisOutputStream(OutputStream out) {
        super(out);
        buff = new byte[8192];
    }


    public void write(byte b) throws IOException {
        if (count == buff.length) {
            out.write(buff);
            count = 0;
        }
        buff[count++] = b;
    }

    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        if (len > buff.length) {
            flushBuff();
            out.write(b, 0, len);
        } else {
            if (len > buff.length - count) {

                flushBuff();
            }
            System.arraycopy(b, off, buff, count, len);
            count += len;
        }
    }

    /**
     * 将buffer字节输入out中
     *
     */
    private void flushBuff() throws IOException {
        if (count > 0) {
            out.write(buff, 0, count);
            count = 0;
        }
    }

    public void writeIntCRLF(int value) throws IOException {
         //value 小于0做什么处理？
        if(value<0){

        }
        //获取value的位数
        int digitLen = 0;
        while(value>intSizeTable[digitLen++]);

        if(digitLen>buff.length-count){
            flushBuff();
        }

        while(digitLen>0){
            digitLen--;
            int quotient = value/moduleTable[digitLen];
            value%=moduleTable[digitLen];

            buff[count++] = digitBytes[quotient];
        }
        writeCRLF();

    }

    public void writeCRLF() throws IOException {
        if (2 > buff.length - count) {
            flushBuff();
        }
        buff[count++] = '\r';
        buff[count++] = '\n';
    }

    @Override
    public void flush() throws IOException {
        flushBuff();
        super.flush();
    }
}
