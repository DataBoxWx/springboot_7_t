package com.wkcto.springboot01.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ClassName:JspController
 * package:com.wkcto.springboot01.web
 * Description:
 *
 * @Data:2018/7/25 10:44
 * @Auther:wangxin
 */
@Controller
public class JspController {

    @GetMapping(value = "/boot/jsp")
    public  String jsp(Model model){
        model.addAttribute("msg","springboot中的jsp访问");
        return "index";
    }
}
