package com.example.redis.client;

import org.junit.Before;
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


    RedisClient client = null;

    @Before
    public void initClient(){
        client = new RedisClient("118.31.122.126",6379);
    }

    @Test
    public void ping(){

        System.out.println(client.ping("hello redis"));


    }

    @Test
    public void set(){
//        System.out.println(client.set("test","test-value"));

    }

    @Test
    public void get(){
        System.out.println(client.get("test-2"));

    }

    @Test
    public void keys(){
//        System.out.println(client.keys("tes*"));

    }
    @Test
    public void incr(){
        System.out.println(client.incr("test-2"));

    }
    @Test
    public void clientList(){
        System.out.println(client.clientList());

    }
}
