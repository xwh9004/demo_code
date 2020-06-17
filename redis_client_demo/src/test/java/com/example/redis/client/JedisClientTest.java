package com.example.redis.client;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 10:42 on 2020/6/17
 * @version V0.1
 * @classNmae JedisClientTest
 */
@Slf4j
public class JedisClientTest {

    @Test
    public void connect(){
        Jedis client = new Jedis("118.31.122.126");
//        client.connect();
        log.info("{}",client.ping("hello"));
        log.info("{}",client.clientList());

    }
}
