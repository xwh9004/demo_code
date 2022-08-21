package com.mq.learning.springboot;

import com.mq.learning.entity.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xwh90
 * @date: 2022/8/21 21:08
 * @description:
 */
@RestController
public class RabbitMqController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public String sendMessage(){
        final String routKey = "test.direct.message";
        String exchangeName = "test.direct.exchange";
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.convertAndSend(exchangeName, routKey, new User("Jesse", 20));
        return "ok";
    }
}
