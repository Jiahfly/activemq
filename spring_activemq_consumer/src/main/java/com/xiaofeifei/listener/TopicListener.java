package com.xiaofeifei.listener;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * topic的监听器
 */
@Component  //放入到spring的ioc容器，bean的名称默认是将类的首字母小写
public class TopicListener implements MessageListener {
    //用于接收消息
    @Override
    public void onMessage(Message message) {
        if(message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println(textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
