package com.mq.learning.springboot;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: xwh90
 * @date: 2022/8/21 20:27
 * @description:
 */
@Slf4j
@Component
public class RabbitmqListener {

    @RabbitListener(queues = {"test.direct.queue"})
    public void onMessage(Message message, Channel channel) throws Exception {
        log.info("RabbitmqListener message ={}",new String(message.getBody()));
        log.info("消息接收成功");
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}
