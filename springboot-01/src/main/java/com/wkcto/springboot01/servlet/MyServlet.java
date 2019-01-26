package com.wkcto.springboot01.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName:${NAME}
 * package:${PACKAGE_NAME}
 * Description:
 *
 * @Data:2018/7/25 14:20
 * @Auther:wangxin
 */
@WebServlet(name = "MyServlet",urlPatterns = {"/myServlet"})
public class MyServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("Myservlet  我的进入  ****");
    }
}
