package com.xiaofeifei.producer;

import com.xiaofeifei.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 演示springboot与activemq的整合
 *
 * 消息的生产者
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class SpringBootProducer {

    // 用于工具类发送消息
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Test
    public void ptpSender() {
        /**
         *
         * arg1：对列的名称或主题的名称,由于配置文件中我们设置的是点对点模式，所以是queue
         * arg2：消息的内容
         */
        jmsMessagingTemplate.convertAndSend("springboot_queue", "spring boot message");
    }
}
