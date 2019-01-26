package com.wkcto.activemq;

import com.wkcto.activemq.service.ActivemqMessage;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ClassName:Test1
 * package:com.wkcto.activemq
 * Description:
 *
 * @Data:2018/8/4 9:35
 * @Auther:wangxin
 */
public class Test1 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ActivemqMessage activemqMessage = (ActivemqMessage) context.getBean("activemqMessage");
       activemqMessage.sendMessage();
        //String text = activemqMessage.receiveMessage();
        System.out.println("发送结束");
    }
}
