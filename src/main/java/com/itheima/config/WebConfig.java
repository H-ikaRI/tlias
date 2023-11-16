package com.itheima.config;

import com.itheima.interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName: WebConfig
 * Package: com.itheima.config
 * Description:
 *
 * @Author: Hjr
 * @Create 2023/11/9 20:20
 * @Version 1.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginCheckInterceptor l;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(l).addPathPatterns("/**");
    }
}
