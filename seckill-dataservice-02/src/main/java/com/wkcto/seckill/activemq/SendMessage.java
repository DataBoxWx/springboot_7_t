package com.wkcto.seckill.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

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
@Component
public class SendMessage {

    @Autowired
    private  JmsTemplate jmsTemplate;

    public  void sendMessage(String json){
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(json);
            }
        });
    }
}
