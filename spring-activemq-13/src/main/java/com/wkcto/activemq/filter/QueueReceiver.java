package com.wkcto.activemq.filter;

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

           // String seletor = "name = '唐嫣'";
            //c创建消费者
           // consumer = session.createConsumer(destination,seletor);
            consumer = session.createConsumer(destination);
            //接收连接之前需要把连接启动一下
            connection.start();

            //发送消息

            while (true){
                Message message = consumer.receive();
                if(message instanceof TextMessage){
                    String text = ((TextMessage) message).getText();
                    System.out.println(text);
                }
                System.out.println("消息接收over");
            }

        } catch (JMSException e) {
            e.printStackTrace();
        }finally {

            try {
                if(connection != null){
                    connection.close();
                }
                if(session != null){
                    session.close();
                }
                if(consumer != null){
                    consumer.close();
                }

            } catch (JMSException e) {
                e.printStackTrace();
            }

        }


    }
}
