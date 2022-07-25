package com.test.mq.learning;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.springframework.amqp.core.ExchangeTypes.DIRECT;

/**
 * mq 消费者
 */
public class Consumer {
    public static void main(String[] args) throws InterruptedException {
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
             final Channel channel = conn.createChannel();) {
            while (true) {
                //发送消息
                channel.basicConsume("test.direct.queue", new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope,
                                               AMQP.BasicProperties properties, byte[] body) throws IOException {
                        System.out.println("接受到消息:" + new String(body));
                        long deliveryTag = envelope.getDeliveryTag();
                        System.out.println("deliveryTag:" + deliveryTag);
                        channel.basicAck(deliveryTag, false);
                    }
                });
            }
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }
}
