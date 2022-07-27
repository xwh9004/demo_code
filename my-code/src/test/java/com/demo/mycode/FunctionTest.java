package com.demo.mycode;

import java.util.function.Function;

public class FunctionTest {

    public static Function<Applier, String> applier;

    public static void main(String[] args) {
        Applier input = new Applier();
        applier = Applier::getName;
        String result = applier.apply(input);
        System.out.println(result);
    }

}
