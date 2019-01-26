package com.wkcto.springboot01.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * ClassName:${NAME}
 * package:${PACKAGE_NAME}
 * Description:
 *
 * @Data:2018/7/25 14:34
 * @Auther:wangxin
 */
public class HeFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("he filter ......");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
