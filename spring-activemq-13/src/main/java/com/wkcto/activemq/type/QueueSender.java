package com.wkcto.activemq.type;

import com.alibaba.fastjson.JSONObject;
import com.wkcto.activemq.Person;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * ClassName:QueueSender
 * package:com.wkcto.activemq.queue
 * Description:点对点消息发送
 *
 * @Data:2018/8/3 9:49
 * @Auther:wangxin
 */
public class QueueSender {

    //消息服务器的连接地址
    public static final String BROKER_URL = "tcp://192.168.10.129:61616";

    //消息目的地
    public static final String DESTINATION_NAME = "Queue";
    public static void main(String[] args) {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        Connection connection = null;
        Session session = null;

        MessageProducer producer = null;
        try {
            //创建连接
            connection = connectionFactory.createConnection();

            //创建session,jms 1.1规范
            session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE   );

            Person person = new Person();
            person.setName("刘亦菲");
            person.setSex("男");
            //创建消息
         //   ObjectMessage message = session.createObjectMessage(person);
          /*  MapMessage mapMessage = session.createMapMessage();
            mapMessage.setBoolean("flag",true);
            mapMessage.setByte("byte",(byte) 1);*/
            /*BytesMessage message = session.createBytesMessage();
            message.writeBoolean(false);
            message.writeChar('女');
            message.writeDouble(1.1);*/
           /* StreamMessage message = session.createStreamMessage();
            message.writeBoolean(true);
            message.writeInt(101);*/

            String jsonString = JSONObject.toJSONString(person);
            TextMessage message = session.createTextMessage(jsonString);

            //创建目的地
            Destination  destination = session.createQueue(DESTINATION_NAME);

            //c创建消息生产者
            producer = session.createProducer(destination);

            //发送消息
            producer.send(message);
            System.out.println("消息发送over");
            session.commit();


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
                    if(producer != null){
                        producer.close();
                    }

                } catch (JMSException e) {
                    e.printStackTrace();
                }

        }


    }
}
