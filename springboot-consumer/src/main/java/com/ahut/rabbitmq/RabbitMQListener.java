package com.ahut.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author hanzijian
 * @create 2020-10-04 21:14
 */
@Component
public class RabbitMQListener {
    @RabbitListener(queues = "boot_topic_queue")
    public void ListenerQueue(Message message){
        System.out.println(new String(message.getBody()));

    }
}
