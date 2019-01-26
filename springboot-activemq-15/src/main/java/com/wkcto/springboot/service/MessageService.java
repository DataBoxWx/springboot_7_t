package com.wkcto.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * ClassName:MessageService
 * package:com.wkcto.springboot.service
 * Description:
 *
 * @Data:2018/8/5 10:21
 * @Auther:wangxin
 */
@Service
public class MessageService {

    @Autowired
    private JmsTemplate jmsTemplate;

    public  void sendMessage(){
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("springboot activemq 集成");
            }
        });
    }

    public  String receiveMessage(){
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
