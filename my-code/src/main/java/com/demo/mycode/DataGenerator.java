package com.demo.mycode;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DataGenerator {

    public static void main(String[] args) {

        Random random = new Random();
        IntStream integers = IntStream.generate(() -> random.nextInt(100));
        List list =  Arrays.asList(integers.toArray());
        Collections.shuffle(list);
        long start = System.currentTimeMillis();
        Collections.sort(list);
    }
}
