package com.demo.thread;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Calculator implements Runnable {

    public void run() {
        long current =1L;
        long max =200L;
        long numPrimes =0L;
        System.out.printf("Thread '%s': START\n",Thread.currentThread().getName());
        while (current<=max){
            if(isPrimes(current)){
                numPrimes++;
            }
            current++;
        }
        System.out.printf("Thread '%s': END. Number of primes: %d\n",Thread.currentThread().getName(),numPrimes);
    }

    private boolean isPrimes(long number) {
        if(number<=2){
            return true;
        }
        for(long i=2;i<number;i++){
            if((number%i)==0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.printf("Minimum Priority: %s\n",Thread.MIN_PRIORITY);
        System.out.printf("Normal Priority: %s\n",Thread.NORM_PRIORITY);
        System.out.printf("Maximum Priority: %s\n",Thread.MAX_PRIORITY);
        int count =10;
        Thread[] threads = new Thread[count];
        Thread.State states[] =new Thread.State[count];
        for (int i=0;i<count;i++){
            threads[i] = new Thread(new Calculator());
            if((i%2)==0){
                threads[i].setPriority(Thread.MAX_PRIORITY);
            }else {
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }
            threads[i].setName("My Thread "+i);
        }
        try(FileWriter fileWriter = new FileWriter("log.txt")) {
            PrintWriter pw = new PrintWriter(fileWriter);
            for(int i =0;i<count;i++){
                pw.println("Main : Status of Thread "+i+":"+threads[i].getState());
                states[i] = threads[i].getState();
            }
            for(int i =0;i<count;i++){
               threads[i].start();
            }
            boolean finish = false;
            while (!finish){
                for(int i =0;i<count;i++){
                   if(threads[i].getState()!=states[i]){
                       writeThreadInfo(pw,threads[i],states[i]);
                       states[i] = threads[i].getState();
                   }
                }
                finish = true;
                for(int i =0;i<count;i++){
                   finish = finish&& (threads[i].getState()== Thread.State.TERMINATED);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void writeThreadInfo(PrintWriter pw, Thread thread, Thread.State state) {
        pw.printf("Main : Id %d -%s\n",thread.getId(),thread.getName());
        pw.printf("Main : Priority %d\n",thread.getPriority());
        pw.printf("Main : Old State :%s\n",state);
        pw.printf("Main : New State :%s\n",thread.getState());
        pw.printf("Main : ************************************************\n");
    }
}
