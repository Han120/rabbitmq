package com.ahut.test;

import com.ahut.rabbitmq.config.RabbitMQConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author hanzijian
 * @create 2020-10-04 21:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProducerTest {
    //1. 注入RabbitTemplate

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Test
    public void testSend(){
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,"boot.haha","spring boot rabbitmq...");
    }
}
