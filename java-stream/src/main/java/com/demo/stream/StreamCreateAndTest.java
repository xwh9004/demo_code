package com.demo.stream;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamCreateAndTest {

    public Stream<String> stream;

    @Before
    public void streamCreateByStreamOf(){
        stream = Stream.of("one", "two", "three", "four", "five");
    }
    @Test
    public void streamCreate(){
        BigInteger limit = new BigInteger("20");
        //产生一个无限大小流
//        Stream<BigInteger> integers2 =Stream.iterate(BigInteger.TEN,n->n.add(BigInteger.TWO));
        //产生 从 10 开始 到20 的步长位2的整数流
        Stream<BigInteger> integers =Stream.iterate(BigInteger.TEN,n-> n.compareTo(limit)<0,n->n.add(BigInteger.TWO));
        printStreamElement(integers);
//        System.out.println(integers.findFirst());
    }

    public void printStreamElement(Stream stream){
        StringBuilder sb = new StringBuilder();
        Object[] array = stream.toArray();
        for (Object a:array){
            sb.append(a.toString()).append(",");
        }
        System.out.println(sb.substring(0,sb.length()-1));
    }



    @Test
    public void anyMatchTest(){
        boolean bool = stream.anyMatch(s -> s.equals("two")); //bool = true
        bool = stream.anyMatch(s -> s.equals("ten")); //boo2 = false;
        System.out.println(bool);

    }


    @Test
    public void nonMatchTest(){
        boolean bool = stream.noneMatch(s -> s.equals("ten")); //boo2 = false;
        System.out.println(bool);
    }

    @Test
    public void allMatchTest(){
        boolean bool = stream.allMatch(s -> s.equals("one")); //boo2 = false;
        System.out.println(bool);
    }
    @Test
    public void filterTest(){
        Optional<String> opt = stream.filter(s -> s.startsWith("h")).findFirst();
        System.out.println(opt.orElse(""));
    }

    /**
     * 多线程并行执行流操作
     */
    @Test
    public void parallelTest(){
        Optional<String> opt = stream.parallel().filter(s -> {
            System.out.println("who get answer thread id is = "+ Thread.currentThread().getId());
            return  s.startsWith("t");
        }).findFirst();
        System.out.println(opt.orElse(""));
    }







}
