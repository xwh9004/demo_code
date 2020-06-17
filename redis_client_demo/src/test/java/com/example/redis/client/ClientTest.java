package com.example.redis.client;

import org.junit.Test;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 17:35 on 2020/6/15
 * @version V0.1
 * @classNmae ClientTest
 */
public class ClientTest {

    @Test
    public void ping(){
        RedisClient client = new RedisClient("118.31.122.126",6379);
        System.out.println(client.ping());

    }

    @Test
    public void set(){
        RedisClient client = new RedisClient("118.31.122.126",6379);
        System.out.println(client.set("test","test-value"));

    }

    @Test
    public void get(){
        RedisClient client = new RedisClient("118.31.122.126",6379);
        System.out.println(client.get("test"));

    }

    @Test
    public void keys(){
        RedisClient client = new RedisClient("118.31.122.126",6379);
        System.out.println(client.keys("tes*"));

    }

    @Test
    public void clientList(){
        RedisClient client = new RedisClient("118.31.122.126",6379);
        System.out.println(client.clientList());

    }
}
