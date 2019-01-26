package com.wkcto.springboot.controller;

import com.wkcto.springboot.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName:MessageController
 * package:com.wkcto.springboot.controller
 * Description:
 *
 * @Data:2018/8/5 10:27
 * @Auther:wangxin
 */
@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

   @RequestMapping("/activemq/send")
   @ResponseBody
    public String sendMessage(){
        messageService.sendMessage();
       System.out.println("消息发送完成");
       return "OK";
    }


    @RequestMapping("/activemq/receive")
    @ResponseBody
    public String receiveMessage(){
        String message = messageService.receiveMessage();
        return message;
    }
}
