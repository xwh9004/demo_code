package com.learning.jvm;

/**
 * -verbose:gc
 * -Xms20M
 * -Xmx20M
 * -Xmn10M
 * -XX:+PrintGCDetails
 * -XX:SurvivorRatio=8
 * -XX:MaxTenuringThreshold=15
 * -XX:+UseParNewGC
 */
public class TenuringThrehold2 {
    private static final  int _1M =1024*1024;
    public static void testTenuringThreshold(){
        byte[] allocation1,allocation2,allocation3,allocation4;
        allocation1 = new byte[_1M/4];
        allocation2 = new byte[_1M/4];

        allocation3 = new byte[4*_1M];
        allocation4 = new byte[4*_1M];
        allocation4 = null;
        allocation4 = new byte[4*_1M];
    }

    public static void main(String[] args) {
        TenuringThrehold2.testTenuringThreshold();
    }
}
