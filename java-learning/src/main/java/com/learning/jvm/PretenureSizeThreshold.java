package com.learning.jvm;

/**
 * -verbose:gc
 * -Xms20M
 * -Xmx20M
 * -Xmn10M
 * -XX:+PrintGCDetails
 * -XX:SurvivorRatio=8
 * -XX:PretenureSizeThreshold=3145728  //该参数只对ParNew 和Serial有用
 * 因此需要加上-XX:+UseParNewGC 或 -XX:+UseConcMarkSweepGC
 */
public class PretenureSizeThreshold {
    private static final  int _1M =1024*1024;
    public static void testPretenureSizeThreshold(){
        byte[] allocation;
        allocation = new byte[4*_1M];
    }

    public static void main(String[] args) {
        PretenureSizeThreshold.testPretenureSizeThreshold();
    }
}

/**
 *
 *Heap
 *  par new generation   total 9216K, used 5944K [0x00000000f9a00000, 0x00000000fa400000, 0x00000000fa400000)
 *   eden space 8192K,  72% used [0x00000000f9a00000, 0x00000000f9fce3e8, 0x00000000fa200000)
 *   from space 1024K,   0% used [0x00000000fa200000, 0x00000000fa200000, 0x00000000fa300000)
 *   to   space 1024K,   0% used [0x00000000fa300000, 0x00000000fa300000, 0x00000000fa400000)
 *  tenured generation   total 10240K, used 0K [0x00000000fa400000, 0x00000000fae00000, 0x00000000fae00000)
 *    the space 10240K,   0% used [0x00000000fa400000, 0x00000000fa400000, 0x00000000fa400200, 0x00000000fae00000)
 *  compacting perm gen  total 21248K, used 2940K [0x00000000fae00000, 0x00000000fc2c0000, 0x0000000100000000)
 *    the space 21248K,  13% used [0x00000000fae00000, 0x00000000fb0df3c0, 0x00000000fb0df400, 0x00000000fc2c0000)
 * No shared spaces configured.
*/

/**
*-XX:PretenureSizeThreshold=3145728
* Heap
 *  par new generation   total 9216K, used 1848K [0x00000000f9a00000, 0x00000000fa400000, 0x00000000fa400000)
 *   eden space 8192K,  22% used [0x00000000f9a00000, 0x00000000f9bce3d8, 0x00000000fa200000)
 *   from space 1024K,   0% used [0x00000000fa200000, 0x00000000fa200000, 0x00000000fa300000)
 *   to   space 1024K,   0% used [0x00000000fa300000, 0x00000000fa300000, 0x00000000fa400000)
 *  tenured generation   total 10240K, used 4096K [0x00000000fa400000, 0x00000000fae00000, 0x00000000fae00000)
 *    the space 10240K,  40% used [0x00000000fa400000, 0x00000000fa800010, 0x00000000fa800200, 0x00000000fae00000)
 *  compacting perm gen  total 21248K, used 2952K [0x00000000fae00000, 0x00000000fc2c0000, 0x0000000100000000)
 *    the space 21248K,  13% used [0x00000000fae00000, 0x00000000fb0e22b8, 0x00000000fb0e2400, 0x00000000fc2c0000)
 * No shared spaces configured.
*/