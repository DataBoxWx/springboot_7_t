package com.wkcto.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:WarController
 * package:com.wkcto.springboo.controller
 * Description:
 *
 * @Data:2018/7/27 10:00
 * @Auther:wangxin
 */
@Controller
public class JarController {

    @RequestMapping("/boot/json")
    public @ResponseBody Object json(){
        Map<String ,Object> json = new HashMap<>();

        json.put("id","11111111111");
        json.put("nme","ssssssssss");
        return  json;
    }

    @RequestMapping("/boot/index")
    public String index(Model model){
        model.addAttribute("msg","这是一个index页面");
        return "index";
    }
}
