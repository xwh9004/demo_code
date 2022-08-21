package com.test.mq.learning.spring;

import com.test.mq.learning.entity.User;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.TimeUnit;

/**
 * @author: xwh90
 * @date: 2022/8/21 9:58
 * @description:
 */
@EnableRabbit
@Slf4j
@ComponentScan
public class RabbitMqApplication {

    @SneakyThrows
    public static void main(String[] args) {
//        publishMessage();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RabbitMqApplication.class);
        //接收string 消息
        TimeUnit.SECONDS.sleep(60);
        context.close();

    }

    private static void publishMessage() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RabbitMqApplication.class);

        final RabbitTemplate rabbitTemplate = context.getBean(RabbitTemplate.class);
        String exchangeName = "test.direct.exchange";
//        final String queueName = "test.direct.queue";
        final String routKey = "test.direct.message";
        //发送 string 消息
//        rabbitTemplate.convertAndSend(exchangeName,routKey,"hell direct queue");
        //发送对象消息
        // rabbitTemplate.convertAndSend(exchangeName,routKey,new User("Jesse",18));
        //将对象消息转为json
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.convertAndSend(exchangeName, routKey, new User("Jesse", 20));

        rabbitTemplate.convertAndSend(exchangeName, routKey, new User("Jesse", 21), new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                return message;
            }
        });
        System.out.println("消息发送成功");
        context.close();
    }

    private static void declareExchangeAndQueueBySpringContainer() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RabbitMqApplication.class);
        final ConnectionFactory connectionFactory = context.getBean(ConnectionFactory.class);
        //让spring 容器代理创建exchange queue，binding ,需要创建连接
        final Connection connection = connectionFactory.createConnection();
        connection.close();
        context.close();
    }


    public static void declareExchangeAndQueueManual() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RabbitMqApplication.class);
        context.start();
        final RabbitAdmin rabbitAdmin = context.getBean(RabbitAdmin.class);
        String exchangeName = "test.direct.exchange";
        //声明一个exchange,其内部创建一个connection
        rabbitAdmin.declareExchange(new DirectExchange(exchangeName, true, false));
        //声明一个queue
        final String queueName = "test.direct.queue";
        rabbitAdmin.declareQueue(new Queue(queueName, true));
        //声明一个Binding
        rabbitAdmin.declareBinding(new Binding(queueName, Binding.DestinationType.QUEUE, exchangeName, "test.direct.message", null));

        System.out.println("手动声明 exchange,queue,binding success");

        rabbitAdmin.deleteExchange(exchangeName);
        rabbitAdmin.deleteQueue(queueName);
        context.close();
    }


}
