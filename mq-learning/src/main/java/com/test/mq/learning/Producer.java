package com.test.mq.learning;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import static org.springframework.amqp.core.ExchangeTypes.DIRECT;

/**
 * mq 消息生产者
 */
public class Producer {

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        try {
            factory.setUri("amqp://Jesse:Jesse@localhost:5672/test");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        try (Connection conn = factory.newConnection();
             final Channel channel = conn.createChannel()) {
            //声明一个交换机
            channel.exchangeDeclare("test.direct.exchange", DIRECT);
            //发送消息
            byte[] messageBodyBytes = "Hello, RabbitMq 6!".getBytes();
            channel.basicPublish("test.direct.exchange", "test.direct.routingKey", null, messageBodyBytes);
            System.out.println("消息发送成功啦");
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
