package com.ahut.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hanzijian
 * @create 2020-10-04 21:07
 */
@Configuration
public class RabbitMQConfig {
    public static final String EXCHANGE_NAME="boot_topic_exchange";
    public static final String QUEUE_NAME="boot_topic_queue";
    //1.交换机 exchange
    @Bean("bootExchange")
    public Exchange exchange(){
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
    }

    //2.队列 queue
    @Bean("bootQueue")
    public Queue queue(){
        return QueueBuilder.durable(QUEUE_NAME).build();
    }
    //3.队列与交换机的绑定关系 Binding
    @Bean
    public Binding binding(@Qualifier("bootQueue") Queue queue, @Qualifier("bootExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("boot.#").noargs();
    }
}
