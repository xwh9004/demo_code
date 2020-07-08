package com.demo.jvm;

/**
 * -verbose:gc
 * -Xms20M
 * -Xmx20M
 * -Xmn10M
 * -XX:+PrintGCDetails
 * -XX:SurvivorRatio=8
 * -XX:MaxTenuringThreshold=1
 * -XX:+PrintTenuringDistribution
 * -XX:+UseParNewGC
 */

import java.io.IOException;

/**
 * -XX:+UseConcMarkSweepGC
 * 22%
 */
public class TenuringThreshold {

    private static final  int _1M =1024*1024;
    public static void testTenuringThreshold(){
        byte[] allocation1,allocation2,allocation3;
        allocation1 = new byte[_1M/4];
        allocation2 = new byte[4*_1M];
        allocation3 = new byte[4*_1M];
        allocation3 = null;
        allocation3 = new byte[4*_1M];
    }

    public static void main(String[] args) throws IOException {
        TenuringThreshold.testTenuringThreshold();
        System.in.read();
    }
}
/**
 * -XX:MaxTenuringThreshold=1
 * Heap
 *  par new generation   total 9216K, used 4484K [0x00000000f9a00000, 0x00000000fa400000, 0x00000000fa400000)
 *   eden space 8192K,  51% used [0x00000000f9a00000, 0x00000000f9e14820, 0x00000000fa200000)
 *   from space 1024K,  29% used [0x00000000fa200000, 0x00000000fa24cae8, 0x00000000fa300000)
 *   to   space 1024K,   0% used [0x00000000fa300000, 0x00000000fa300000, 0x00000000fa400000)
 *  tenured generation   total 10240K, used 4901K [0x00000000fa400000, 0x00000000fae00000, 0x00000000fae00000)
 *    the space 10240K,  47% used [0x00000000fa400000, 0x00000000fa8c94a0, 0x00000000fa8c9600, 0x00000000fae00000)
 *  compacting perm gen  total 21248K, used 2941K [0x00000000fae00000, 0x00000000fc2c0000, 0x0000000100000000)
 *    the space 21248K,  13% used [0x00000000fae00000, 0x00000000fb0df408, 0x00000000fb0df600, 0x00000000fc2c0000)
 */