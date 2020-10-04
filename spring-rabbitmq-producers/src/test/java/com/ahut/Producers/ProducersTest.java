package com.ahut.Producers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author hanzijian
 * @create 2020-10-04 20:01
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-rabbitmq-producer.xml")
public class ProducersTest {
    //1.注入RabbitTemplate
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testHelloWorld(){
        //2.发送消息
        rabbitTemplate.convertAndSend("spring_queue","hello world spring....");
    }
    @Test
    public void testFanout(){
        //2.发送消息
        rabbitTemplate.convertAndSend("spring_fanout_exchange","","fanout spring...");
    }
    @Test
    public void testTopic(){
        //2.发送消息
        rabbitTemplate.convertAndSend("spring_topic_exchange","heima.hehe.haha","topic  spring....");
    }
}
