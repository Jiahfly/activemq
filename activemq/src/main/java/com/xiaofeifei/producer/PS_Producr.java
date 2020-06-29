package com.xiaofeifei.producer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 发布订阅的生产者
 *  // 1. 创建连接工厂
 *  // 2. 创建连接
 *  // 3. 打开连接
 *  // 4. 创建session
 *  // 5. 创建目标地址（Queue 是点对点，Top 是订阅发布）
 *  // 6. 创建消息生产者
 *  // 7. 创建消息
 *  // 8. 发送消息
 *  // 9. 关闭资源
 */
public class PS_Producr {


    public static void main(String[] args) throws JMSException {

        // 1 创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://49.233.37.118:61616");
        // 2.创建连接
        Connection connection = connectionFactory.createConnection();
        // 3.打开连接
        connection.start();
        // 4.创建session, arg1:是否开启事务，arg2:消息的确认机制,有四个值，我们这里用auto_acknowledge
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 5. 创建目的Queue 是点对点，Top 是订阅发布）,arg1：是queue的名字
        Topic topic01 = session.createTopic("topic01");

        // 6. 创建消息的生产者
        MessageProducer producer = session.createProducer(topic01);
        // 7. 创建消息,我们这里使用的是文本
        TextMessage testMessage = session.createTextMessage("test message -- this is a topic");
        // 8. 发送消息
        producer.send(testMessage);
        // 9. 释放资源
        session.close();
        connection.close();

    }
}
