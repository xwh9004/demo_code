package com.demo.kafka;

public interface Producer {

    void send(Order order);

    void close();

    // add your interface method here

    // and then implement it

}
