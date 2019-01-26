package com.wkcto.activemq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * ClassName:ActivemqMessage
 * package:com.wkcto.activemq.service
 * Description:
 *
 * @Data:2018/8/4 9:30
 * @Auther:wangxin
 */
@Component
public class ActivemqMessage {
    @Autowired
    private JmsTemplate jmsTemplate;

    //发送消息
    public  void sendMessage(){
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("啦啦我是一个消息啦，你收到了吗？");
            }
        });
    }

    //接收消息
    public String receiveMessage(){
        Message message = jmsTemplate.receive();
        String text = null;
        if(message instanceof TextMessage){
            try {
                text = ((TextMessage) message).getText();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
        return text;

    }
}
