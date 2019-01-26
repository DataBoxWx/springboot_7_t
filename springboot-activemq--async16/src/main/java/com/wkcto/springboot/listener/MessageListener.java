package com.wkcto.springboot.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * ClassName:MessageListener
 * package:com.wkcto.springboot.listener
 * Description:
 *
 * @Data:2018/8/5 10:56
 * @Auther:wangxin
 */
@Component
public class MessageListener  {

    @JmsListener(destination = "${spring.jms.template.default-destination}")
    public void MessageListener(Message message){
        String text = null;
        if(message instanceof TextMessage){

            try {
                text = ((TextMessage) message).getText();
            } catch (JMSException e) {
                e.printStackTrace();
            }

        }
        System.out.println("收到的消息：" + text);
    }
}
