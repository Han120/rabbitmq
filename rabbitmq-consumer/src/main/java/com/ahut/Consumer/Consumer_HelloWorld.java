package com.ahut.Consumer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author hanzijian
 * @create 2020-10-03 15:56
 */
public class Consumer_HelloWorld {
    public static void main(String[] args) throws IOException, TimeoutException {
        //1.创建连接工厂
        ConnectionFactory connectionFactory=new ConnectionFactory();
        //2.设置参数
        connectionFactory.setHost("localhost");// 设置ip 默认是localhost
        connectionFactory.setPort(5672);//设置端口 默认是5672
        connectionFactory.setVirtualHost("/admin"); //设置虚拟机 默认是/
        connectionFactory.setUsername("admin"); //设置用户名
        connectionFactory.setPassword("admin"); //设置密码
        //3.创建连接
        Connection connection=connectionFactory.newConnection();
        //4. 创建channel
        Channel channel=connection.createChannel();
        //5.创建队列 queue
//        channel.queueDeclare("hello_world",true,false,false,null);
        //6.接收消息
        Consumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {//收到消息自动执行
                System.out.println("body"+new String(body));
            }
        };
        channel.basicConsume("hello_world",true,consumer);

    }
}
