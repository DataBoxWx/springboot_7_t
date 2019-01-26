package com.wkcto.springboot01.web;

import com.wkcto.springboot01.config.ConfigInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName:HelloController
 * package:com.wkcto.web
 * Description:
 *
 * @Data:2018/7/24 14:31
 * @Auther:wangxin
 */
@Controller
public class HelloController {

    @Value("${info.site.url}")
    private  String url;

    @Value("${info.site.tel}")
    private String tel;

    @Autowired
    private ConfigInfo configInfo;

    @RequestMapping(value = "/boot/info")
    public @ResponseBody String config(){

        return "url : " + configInfo.getUrl()  +"  *** tel : " + configInfo.getTel();
    }
}
