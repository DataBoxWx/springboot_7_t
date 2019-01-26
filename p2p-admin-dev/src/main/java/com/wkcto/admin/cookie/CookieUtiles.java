package com.wkcto.admin.cookie;

import com.wkcto.admin.commons.CommonsConstants;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName:CookieUtiles
 * package:com.wkcto.admin.cookie
 * Description:
 *
 * @Data:2018/8/15 10:50
 * @Auther:wangxin
 */
public class CookieUtiles {
    /**
     * 根据cook名字判断是否存在该cook的值
     * @param request
     * @param cookName
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String cookName){
        String cookValue = "";
        Cookie [] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(cookName)){
                    cookValue = cookie.getValue();
                    break;
                }
            }
        }

        return cookValue;
    }

    /**
     * 生成一个cookie并发送到浏览器
     * @param cookName
     * @param cookValue
     * @param request
     * @param response
     */
    public static void addCookie(String cookName, String cookValue, HttpServletRequest request, HttpServletResponse response){
         Cookie cookie = new Cookie(cookName,cookValue);
         cookie.setMaxAge(7 * 24 * 60 * 60);
         cookie.setPath(request.getContextPath());
         response.addCookie(cookie);

    }
}
