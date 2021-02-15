package com.demo.stream;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.stream.Stream;

public class StreamCreate {

    @Test
    public void streamCreate(){
        BigInteger limit = new BigInteger("1000000");
        Stream<BigInteger> integers =Stream.iterate(BigInteger.TWO,n-> n.compareTo(limit)<0,n->n.add(BigInteger.ONE));
        System.out.println(integers.findFirst());
    }







}
