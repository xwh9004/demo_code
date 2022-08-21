package com.test.mq.learning.spring;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: xwh90
 * @date: 2022/8/21 10:03
 * @description:
 */
@Configuration
public class RabbitMqConfig {

    /**
     * 配置连接工厂
     *
     * @return
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUri("amqp://Jesse:Jesse@localhost:5672/test");
        return connectionFactory;
    }

    /**
     * 声明操作类
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        return rabbitAdmin;
    }

    @Bean
    public Exchange directExchange() {
        String exchangeName = "test.direct.exchange";
        DirectExchange directExchange = new DirectExchange(exchangeName, true, false);
        return directExchange;
    }

    @Bean
    public Queue directQueue() {
        String queueName = "test.direct.queue";
        return new Queue(queueName, true);
    }

    @Bean
    public Binding binding(){
        String exchangeName = "test.direct.exchange";
        String queueName = "test.direct.queue";
        final String routKey = "test.direct.message";
        return new Binding(queueName,Binding.DestinationType.QUEUE,exchangeName,routKey,null);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        return rabbitTemplate;
    }

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory){
        SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer();
        final String queueName = "test.direct.queue";
        listenerContainer.setQueueNames(queueName);
        listenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        listenerContainer.setConcurrentConsumers(1);
        listenerContainer.setMaxConcurrentConsumers(3);
        listenerContainer.setConnectionFactory(connectionFactory);
//        listenerContainer.setMessageListener(new MyMessageListener());
//        listenerContainer.setMessageListener();
        MessageListenerAdapter adapter = new MessageListenerAdapter();
        adapter.setDelegate(new MyMessageConsumer());
        listenerContainer.setMessageListener(adapter);
        return listenerContainer;

    }
}
