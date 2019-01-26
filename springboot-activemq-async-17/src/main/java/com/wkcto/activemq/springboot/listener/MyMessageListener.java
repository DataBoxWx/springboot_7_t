package com.wkcto.activemq.springboot.listener;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * ClassName:MessageListener
 * package:com.wkcto.activemq.springboot.listener
 * Description:
 *
 * @Data:2018/8/5 11:26
 * @Auther:wangxin
 */
@Component
public class MyMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        if(message instanceof TextMessage){
            String text = null;
            try {
                text = ((TextMessage) message).getText();
            } catch (JMSException e) {
                e.printStackTrace();
            }
            System.out.println("接收消息：" + text);
        }

    }
}
