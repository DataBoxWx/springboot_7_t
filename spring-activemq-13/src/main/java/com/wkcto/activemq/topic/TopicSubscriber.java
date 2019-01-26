package com.wkcto.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * ClassName:TopicSubscriber
 * package:com.wkcto.activemq.topic
 * Description:
 *
 * @Data:2018/8/3 18:51
 * @Auther:wangxin
 */
public class TopicSubscriber {
    //消息服务器的连接地址
    public static final String BROKER_URL = "tcp://192.168.10.129:61616";

    //消息目的地
    public static final String DESTINATION_NAME = "Topic";
    public static void main(String[] args) {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        Connection connection = null;
        Session session = null;

        MessageConsumer consumer = null;
        try {
            //创建连接
            connection = connectionFactory.createConnection();

            //创建session,jms 1.1规范
            session = connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE   );


            //创建目的地
            Destination destination = session.createTopic(DESTINATION_NAME);

            //c创建消费者
            consumer = session.createConsumer(destination);

            //接收连接之前需要把连接启动一下
            connection.start();

            //发送消息
            while(true){
                Message message = consumer.receive();

                if(message instanceof TextMessage){
                    String text = ((TextMessage) message).getText();
                    System.out.println("接收的消息为：" + text);
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
