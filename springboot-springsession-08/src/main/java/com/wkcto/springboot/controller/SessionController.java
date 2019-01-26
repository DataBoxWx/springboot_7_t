package com.wkcto.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * ClassName:SessionController
 * package:com.wkcto.springboot.controller
 * Description:
 *
 * @Data:2018/7/27 10:41
 * @Auther:wangxin
 */
@RestController
public class SessionController {

    @GetMapping("/boot/setSession")
    public String setSession(HttpSession session){
        session.setAttribute("url","www.baidu.com");
        return "OK";

    }

    @GetMapping("/boot/getSession")
    public String getSession(HttpSession session){
        String url = (String) session.getAttribute("url");
        return url == null ? "no session" : url;
    }
}
