package com.test.mq.learning.spring;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;

/**
 * @author: xwh90
 * @date: 2022/8/21 11:21
 * @description: ChannelAwareMessageListener 可以手动确认消息消费
 */
@Slf4j
public class MyMessageListener implements ChannelAwareMessageListener {


    @Override
    public void containerAckMode(AcknowledgeMode mode) {
        mode = AcknowledgeMode.MANUAL;
    }

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        log.info("message ={}",new String(message.getBody()));
        log.info("消息接收成功");
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}
