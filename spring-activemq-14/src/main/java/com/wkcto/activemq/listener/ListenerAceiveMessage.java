package com.wkcto.activemq.listener;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * ClassName:ListenerAceiveMessage
 * package:com.wkcto.activemq.listener
 * Description:
 *
 * @Data:2018/8/4 9:51
 * @Auther:wangxin
 */
@Component
public class ListenerAceiveMessage implements MessageListener {
    public void onMessage(Message message) {
        if(message instanceof TextMessage){
            String text = null;
            try {
                text = ((TextMessage) message).getText();
            } catch (JMSException e) {
                e.printStackTrace();
            }
            System.out.println(text);
        }
    }
}
