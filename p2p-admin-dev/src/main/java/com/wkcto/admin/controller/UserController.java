package com.wkcto.admin.controller;

import com.wkcto.admin.commons.CommonsConstants;
import com.wkcto.admin.commons.CommonsReturnObject;
import com.wkcto.admin.cookie.CookieUtiles;
import com.wkcto.admin.model.UserInfo;
import com.wkcto.admin.service.UserInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName:UserController
 * package:com.wkcto.admin.controller
 * Description:
 *
 * @Data:2018/8/15 12:05
 * @Auther:wangxin
 */
@Controller
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/admin/login")
    public @ResponseBody Object login(HttpServletRequest request, HttpServletResponse response,
                                      @RequestParam(value = "userName",required = true)String userName,
                                      @RequestParam(value = "password",required = true)String password,
                                      @RequestParam(value = "flag",required = false)Boolean flag){
        CommonsReturnObject commonsReturnObject = new CommonsReturnObject();

        UserInfo userInfo = userInfoService.login(userName, password);
        if(userInfo == null){
            commonsReturnObject.setErrorCode(CommonsConstants.ONE);
            commonsReturnObject.setErrorMessage("用户名或密码错误，请重试");
        }

        request.getSession().setAttribute(CommonsConstants.SESSION_USER,userInfo);

        if(flag){
            CookieUtiles.addCookie(CommonsConstants.COOKIE_USERNAME,userName,request,response);
            CookieUtiles.addCookie(CommonsConstants.COOKIE_PASSWORD,password,request,response);
        }

        commonsReturnObject.setErrorCode(CommonsConstants.ZERO);
        return  commonsReturnObject;
    }
}
