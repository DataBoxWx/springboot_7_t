package com.wkcto.springboot01.config;

import com.wkcto.springboot01.filter.HeFilter;
import com.wkcto.springboot01.interceptor.LoginInterceptor;
import com.wkcto.springboot01.servlet.HeServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName:WebConfig
 * package:com.wkcto.springboot01.config
 * Description:
 *
 * @Data:2018/7/25 11:24
 * @Auther:wangxin
 */
@Configuration//相当于applicationContext-MVC.xml
public class WebConfig implements WebMvcConfigurer{
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截器
        String [] pathPatterns = {
            "/**"
        };
        String [] excludePathPatterns = {
            "/boot/info",
        };
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns(pathPatterns).excludePathPatterns(excludePathPatterns);
    }

    @Bean
    public ServletRegistrationBean heServlet(){
        ServletRegistrationBean<HeServlet> heServlet = new ServletRegistrationBean<>(new HeServlet(), "/heServlet");
        return heServlet;
    }

    @Bean
    public FilterRegistrationBean heFilter(){
        FilterRegistrationBean heFilter = new FilterRegistrationBean(new HeFilter());
        heFilter.addUrlPatterns("/*");
        return heFilter;
    }

    /**
     * 配置字符编码
     * @return
     */
  /*  @Bean
    public FilterRegistrationBean encodingFilter(){
        FilterRegistrationBean encodingFilter = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setForceEncoding(true);
        characterEncodingFilter.setEncoding("GBK");
        encodingFilter.setFilter(characterEncodingFilter);
        encodingFilter.addUrlPatterns("/*");
        return encodingFilter;
    }*/

}
