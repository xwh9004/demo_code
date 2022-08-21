package com.mq.learning.spring;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;

/**
 * @author: xwh90
 * @date: 2022/8/21 11:50
 * @description:
 */
@Slf4j
public class MyMessageConsumer {

    public void handleMessage(byte[] message) throws Exception {
        log.info("MyMessageConsumer consumer ={}",new String(message));
        log.info("消息接收成功");
    }

    public void handleMessage(byte[] body,Channel channel,Message message) throws Exception {
        log.info("MyMessageConsumer handleMessage with Channel consumer ={}",new String(body));
        log.info("消息接收成功");
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}
