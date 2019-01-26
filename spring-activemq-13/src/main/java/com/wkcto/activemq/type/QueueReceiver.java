package com.wkcto.activemq.type;

import com.alibaba.fastjson.JSONObject;
import com.wkcto.activemq.Person;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.ArrayList;
import java.util.List;

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
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        List<String > list = new ArrayList<String>();
        list.add("com.wkcto.activemq");

        connectionFactory.setTrustedPackages(list);
        Connection connection = null;
        Session session = null;

        MessageConsumer consumer = null;
        try {
            //创建连接
            connection = connectionFactory.createConnection();

            //创建session,jms 1.1规范
            session = connection.createSession(Boolean.FALSE,Session.CLIENT_ACKNOWLEDGE);


            //创建目的地
            Destination  destination = session.createQueue(DESTINATION_NAME);

            //c创建消费者
            consumer = session.createConsumer(destination);

            //接收连接之前需要把连接启动一下
            connection.start();

            //发送消息
            Message message = consumer.receive();

            if(message instanceof TextMessage){
                String text = ((TextMessage) message).getText();
                JSONObject jsonObject = JSONObject.parseObject(text);

                System.out.println(jsonObject.getString("name") + jsonObject.getString("sex"));
            }if(message instanceof  ObjectMessage){
                Person person = (Person) ((ObjectMessage)message).getObject();
                String name = person.getName();
                String sex = person.getSex();
                System.out.println("姓名：" + name+ "性别：" + sex);
            }if(message instanceof MapMessage){
              Boolean b = ((MapMessage) message).getBoolean("flag");
              Byte by = ((MapMessage) message).getByte("byte");
                System.out.println("flag:" + b + " by:" + by);
            }if(message instanceof BytesMessage){
                Boolean b = ((BytesMessage) message).readBoolean();
                Character character = ((BytesMessage) message).readChar();
                Double d = ((BytesMessage) message).readDouble();
                System.out.println("b:" + b + " character:" + character + " d :" + d);
            }if(message instanceof  StreamMessage){
                Boolean b = ((StreamMessage) message).readBoolean();
                int x = ((StreamMessage) message).readInt();
                System.out.println("b :" + b + " int:" + x);
            }
            message.acknowledge();

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
