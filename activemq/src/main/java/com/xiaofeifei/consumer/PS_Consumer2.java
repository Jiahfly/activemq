package com.xiaofeifei.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 演示发布订阅模式---消费者 （第二种消息接受方案：推荐此种方案）,发布订阅一定要，先去订阅
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
public class PS_Consumer2 {

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
        MessageConsumer consumer = session.createConsumer(topic01);

        // 7. 设置一个监听器，去接受消息
        consumer.setMessageListener(new MessageListener() {
            // 处理消息
            @Override
            public void onMessage(Message message) {
                if(message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage)message;

                    try {
                        System.out.println(textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        // 由于用了监听器，所以千万不要要关闭连接

    }
}
