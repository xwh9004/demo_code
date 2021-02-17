package com.demo.stream;

import org.junit.Test;

import java.util.Optional;
import java.util.function.Supplier;

public class OptionalTest {

    @Test
    public void get(){
        Optional<String> stringOptional = Optional.of("stringValue");
        System.out.println(stringOptional.get());
    }

    @Test
    public void orElse(){
        Optional<String> stringOptional = Optional.empty();
        System.out.println(stringOptional.orElse("defaultValue"));
    }

    @Test
    public void ifPresent(){
        Optional<String> stringOptional = Optional.empty();
        stringOptional.ifPresent(System.out::println);
    }
    @Test
    public void ifPresentOrElse(){
        Optional<String> stringOptional = Optional.of("1");
        stringOptional.ifPresentOrElse(System.out::println,()-> System.out.println( "Not match"));
    }
    @Test
    public void orThrow(){
        Optional<String> stringOptional = Optional.empty();
        try {
            System.out.println(stringOptional.orElseThrow(()->new RuntimeException("stringOptional is null, you should initialize value")));
        } catch (Throwable throwable) {
            System.out.println(throwable.getMessage());
        }
    }

    @Test
    public void map(){
        Optional<String> stringOptional = Optional.of("stringValue");
        Optional<String> transformed = stringOptional.map(String::toUpperCase);
        System.out.println(stringOptional.get());
        System.out.println(transformed.get());
    }


}
