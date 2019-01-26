package com.wkcto.activemq.queue;

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
    public static final String BROKER_URL = "failover:(tcp://192.168.10.129:61617,tcp://192.168.10.129:61618,tcp://192.168.10.129:61619)";
    public static final String USER_NAME = "system";
    public static final String PASSWORD = "123456";

    //消息目的地
    public static final String DESTINATION_NAME = "Queue";
    public static void main(String[] args) {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USER_NAME,PASSWORD,BROKER_URL);
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

            //c创建消费者
            consumer = session.createConsumer(destination);

            //接收连接之前需要把连接启动一下
            connection.start();

            //发送消息
            while(true){
                Message message = consumer.receive();

                if(message instanceof TextMessage){
                    String text = ((TextMessage) message).getText();
                    System.out.println("接收的消息：" + text);
                }
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
