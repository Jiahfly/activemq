package com.xiaofeifei.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * 演示spring与ActiveMQ整合
 */
@RunWith(SpringJUnit4ClassRunner.class) //junit与spring的整合
@ContextConfiguration("classpath:applicationContext-producer.xml") //加载spring的配置文件
public class SpringProducer {

    // 点对点模式的模板对象
    @Autowired
    @Qualifier("jmsQueueTemplate")
    private JmsTemplate jmsQueueTemplate;

    // 发布订阅模式
    @Autowired
    @Qualifier("jmsTopicTemplate")
    private JmsTemplate jmsTopicTemplate;

    /**
     * 点对点发送
     */
    @Test
    public void ptpSender() {
        /**
         * arg1: 指定队列的名称
         * arg2：MessageCreator接口，我们需要提供该接口的匿名内部类的实现
         *
         */
        jmsQueueTemplate.send("spring_queue", new MessageCreator() {
            // 我们只需要返回发送的消息的内容即可
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage("spring test message");

                return textMessage;
            }
        });
        System.out.println("消息发送已完成");

    }

    /**
     * 发布订阅模式的模板
     */
    @Test
    public void topicSender() {
        /**
         * arg1: 指定队列的名称
         * arg2：MessageCreator接口，我们需要提供该接口的匿名内部类的实现
         *
         */
        jmsTopicTemplate.send("spring_topic", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage("spring test message  topic");

                return textMessage;
            }
        });
    }

}
