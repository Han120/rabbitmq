package com.ahut.Producer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author hanzijian
 * @create 2020-10-03 15:37
 */
public class Producer_Routing {
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
        //5.创建交换机 exchange
        String exchangeName="test_direct";
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT,true,false,null);
        //6.创建队列 queue
        String queue1Name="test_direct_queue1";
        String queue2Name="test_direct_queue2";
        channel.queueDeclare(queue1Name,true,false,false,null);
        channel.queueDeclare(queue2Name,true,false,false,null);
        //7. 绑定队列和交换机
        channel.queueBind(queue1Name,exchangeName,"error");
        channel.queueBind(queue2Name,exchangeName,"info");
        channel.queueBind(queue2Name,exchangeName,"error");
        channel.queueBind(queue2Name,exchangeName,"warning");
        //8.发送消息
        String body="日志信息：zhangsan";
        channel.basicPublish(exchangeName,"info",null,body.getBytes());
        channel.basicPublish(exchangeName,"error",null,body.getBytes());
        channel.basicPublish(exchangeName,"warning",null,body.getBytes());
        //9.释放资源
        channel.close();
        connection.close();

    }
}
