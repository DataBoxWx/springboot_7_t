package com.wkcto.activemq.asyc;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * ClassName:QueueReceiver
 * package:com.wkcto.activemq.queue
 * Description:
 *点对点消息接收者
 * @Data:2018/8/3 10:15
 * @Auther:wangxin
 */
public class QueueReceiver {

    //消息服务器的连接地址
    public static final String BROKER_URL = "tcp://192.168.10.129:61616";

    //消息目的地
    public static final String DESTINATION_NAME = "Queue";
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
            Destination  destination = session.createQueue(DESTINATION_NAME);

            //消费者
            consumer = session.createConsumer(destination);
            //接收连接之前需要把连接启动一下
            connection.start();

            //异步接收
            consumer.setMessageListener(new MessageListener() {

                public void onMessage(Message message) {

                    if(message instanceof TextMessage){
                        String text = null;
                        try {
                            text = ((TextMessage) message).getText();
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                        System.out.println("接受的消息：" + text);
                    }
                }
            });



        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
