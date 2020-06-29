package com.xiaofeifei.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * 用于监听消息（即可以用于队列的监听，也可以用于主题监听）
 */
@Component  //放入的ioc容器
public class MsgListener {

    /**
     *  用于接收消息的方法
     */

    @JmsListener(destination = "springboot_queue")     //这个注解的作用是jsm的listener，destination：queue的名称或者是topic的名称
    public void receiveMessage(Message message) {  //参数我们用Message类型（任何类型的消息都接收），也可以用String，则接收的是String类型的数据
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage)message;
            try {
                System.out.println(textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

    }


}
