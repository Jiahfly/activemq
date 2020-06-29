package com.xiaofeifei.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 演示点对点模式---消费者 （第一种消息接收方案：不推荐）
 *
 *   1. 创建连接工厂
 *   2. 创建连接
 *   3. 打开连接
 *   4. 创建session
 *   5. 制定目标地址（Queue 是点对点，Top 是订阅发布）
 *   6. 创建消息消费者
 *   7. 接收消息
 *   8. 释放资源
 */
public class PTP_Consumer1 {

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
        Queue queue01 = session.createQueue("queue01");
        // 6. 创建消息的生产者
        MessageConsumer consumer = session.createConsumer(queue01);

        // 7. 接受消息
        while(true) {
            Message message = consumer.receive();

            //如果已经没有消息了，就结束，如果还有消息，就继续
            if(message == null) {
                break;
            }

            //判断是什么类型的消息
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                System.out.println(textMessage.getText());
            }
        }

        // 9. 释放资源
        session.close();
        connection.close();
    }
}
