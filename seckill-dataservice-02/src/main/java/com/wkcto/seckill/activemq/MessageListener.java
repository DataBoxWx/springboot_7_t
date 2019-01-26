package com.wkcto.seckill.activemq;

import com.alibaba.fastjson.JSONObject;
import com.wkcto.seckill.model.Orders;
import com.wkcto.seckill.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MessageListener {

    @Autowired
    private GoodsService goodsService;

    @JmsListener(destination = "${spring.jms.template.default-destination}")
    public void MessageListener(Message message){
        String text = null;
        Orders orders = null;
        if(message instanceof TextMessage){

            try {
                text = ((TextMessage) message).getText();
                //处理订单消息
                orders = JSONObject.parseObject(text, Orders.class);
                goodsService.createOrders(orders);

            } catch (JMSException e) {
                e.printStackTrace();
                System.out.println("jms接收消息的异常....");
                //处理异常
                goodsService.processException(orders);
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("生成订单的异常....");
                //处理异常
                goodsService.processException(orders);
            }

        }
    }
}
