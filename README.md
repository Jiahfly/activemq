项目是有关activemq的使用demo：
1.用原生的jms
activemq项目

2. 用spring和activemq整合
spring_activemq：是用的spring和activemq的整合，在test目录下有消息的生产者producer（消息类型有Queue和Topic）
spring_activemq_consumer: 是用的spring和activemq的整合，主类在test目录下（用主类的原因为了使用spring），topic和queue的listener在java目录下
上述两个项目中：其实用了两种方式在test目录下启动spring，一种是用注解的ContextConfiguration，一种是写一个main方法，利用ClassPathXmlApplicationContext

3.用springboot和activemq整合
springboot_activemq :是用springboot和activemq整合的，是消息的生产者，在application.yml文件中：pub-sub-domain 属性为true表示的是topic，如果是false则表示的queue
springboot_activemq_consumer:是用springboot和activmq整合的，是消息的消费者，在application.yml文件中：pub-sub-domain 属性为true表示的是topic，如果是false则表示的queue，在com.xiaofeifei.listener下有写的监听器
上述两个项目，端口号不能一样，因为都要启动