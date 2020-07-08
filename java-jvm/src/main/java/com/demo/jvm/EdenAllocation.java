package com.demo.jvm;

/**
 * -verbose:gc
 * -Xms20M
 * -Xmx20M
 * -Xmn10M
 * -XX:+PrintGCDetails
 * -XX:SurvivorRatio=8
 * -XX:+UseParNewGC
 */

import java.io.IOException;

/**
 * 验证对象有限在eden区
 */

public class EdenAllocation {

    private static final  int _1M =1024*1024;

    public static void testAllocation(){
        byte[] allocation1,allocation2,allocation3,allocation4;
        allocation1 = new byte[2*_1M];
        allocation2 = new byte[2*_1M];
        allocation3 = new byte[2*_1M];
        allocation4 = new byte[4*_1M];
    }

    public static void main(String[] args) throws IOException {
        EdenAllocation.testAllocation();
        System.in.read();
    }
}
