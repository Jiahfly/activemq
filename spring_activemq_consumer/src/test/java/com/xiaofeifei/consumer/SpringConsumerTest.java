package com.xiaofeifei.consumer;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * 用户启动consumer
 */
public class SpringConsumerTest {

    public static void main(String[] args) throws IOException {
        // 加载spring的配置文件
        ClassPathXmlApplicationContext ctx =
                new ClassPathXmlApplicationContext("classpath:applicationContext-producer.xml");
        // 2.启动
        ctx.start();
        // 3.阻塞方法，让程序一直处于等待状态
        System.in.read();
    }
}
