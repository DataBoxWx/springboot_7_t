package com.wkcto.admin.controller;

import com.wkcto.admin.commons.CommonsConstants;
import com.wkcto.admin.cookie.CookieUtiles;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;

/**
 * ClassName:LoginController
 * package:com.wkcto.admin.controller
 * Description:
 *
 * @Data:2018/8/15 9:13
 * @Auther:wangxin
 */
@Controller
public class LoginController {

    @RequestMapping("/")
    public String index(HttpServletRequest request, Model model){
        String userName = CookieUtiles.getCookieValue(request, CommonsConstants.COOKIE_USERNAME);
        String password = CookieUtiles.getCookieValue(request, CommonsConstants.COOKIE_PASSWORD);
        if(StringUtils.isNotEmpty(userName) && StringUtils.isNotEmpty(password)){
            model.addAttribute("userName",userName);
            model.addAttribute("password",password);
            model.addAttribute("flag",1);
        }
        return "login";
    }
    @RequestMapping("/admin/profile")
    public String profile(){
        return "profile";
    }
}
