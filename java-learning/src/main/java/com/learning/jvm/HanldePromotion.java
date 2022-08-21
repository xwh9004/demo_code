package com.learning.jvm;

import java.io.IOException;
import java.util.Map;

/**
 * -verbose:gc
 * -Xms20M
 * -Xmx20M
 * -Xmn10M
 * -XX:+PrintGCDetails
 * -XX:SurvivorRatio=8
 * -XX:-HandlePromotionFailure   //没有这个参数
 * -XX:+UseParNewGC
 * -XX:+PrintTenuringDistribution
 */
public class HanldePromotion {
    private static final  int _1M =1024*1024;
    public static void testHandlerPromotion(){
        byte[] allocation1,allocation2,allocation3,allocation4,allocation5,allocation6,allocation7,allocation8;
        allocation1 = new byte[2*_1M];
        allocation2 = new byte[2*_1M];
        allocation3 = new byte[2*_1M];
        allocation1 = null;
        allocation4 = new byte[2*_1M];
        allocation5 = new byte[2*_1M];
        allocation6 = new byte[2*_1M];
        allocation4 =null;
        allocation5 = null;
        allocation6 = null;
        allocation7 = new byte[2*_1M];
    }

    public static void main(String[] args) throws IOException {
        HanldePromotion.testHandlerPromotion();
       for(Map.Entry<Thread,StackTraceElement[]> stackTrace:Thread.getAllStackTraces().entrySet()){
           Thread thread =(Thread)stackTrace.getKey();
           StackTraceElement[] stack = stackTrace.getValue();
           if(thread.equals(Thread.currentThread())){
               continue;
           }
           System.out.println("\n线程："+ thread.getName()+"\n");
           for (StackTraceElement element:stack){
               System.out.println("\t"+element+"\n");
           }
       };
        System.in.read();
    }
}
