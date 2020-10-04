package com.ahut.Producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.TimeoutException;

/**
 * @author hanzijian
 * @create 2020-10-03 15:37
 */
public class Producer_HelloWorld {
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
        channel.queueDeclare("hello_world",true,false,false,null);
        //6.发送消息
        channel.basicPublish("","hello_world",null,"hello rabbitmq~~~~".getBytes());
        //7.释放资源
        channel.close();
        connection.close();

    }
}
