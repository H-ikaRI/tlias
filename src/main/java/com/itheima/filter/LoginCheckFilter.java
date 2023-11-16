package com.itheima.filter;

import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * ClassName: LoginCheckFilter
 * Package: com.itheima.filter
 * Description:
 *
 * @Author: Hjr
 * @Create 2023/11/8 23:12
 * @Version 1.0
 */
@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1.获取url
        HttpServletRequest sre = (HttpServletRequest)servletRequest;
        HttpServletResponse sqon = (HttpServletResponse)servletResponse;
        String url = sre.getRequestURL().toString();
        log.info("获取的url为{}",url);
        //2.判断url是否有login
        if(url.contains("login")){
            log.info("直接放行");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        //3.获取请求头中的令牌
        String jwt = sre.getHeader("token");
        //4.判断令牌是否存在
        if(!StringUtils.hasLength(jwt)){
            log.info("令牌不存在");
            Result error = Result.error("NOT_LOGIN");
            String notlogin = JSONObject.toJSONString(error);
            sqon.getWriter().write(notlogin);
            return;
        }
        //5.解析token
        try {
            JwtUtils.parseJWT(jwt);
        }catch (Exception e){
            e.printStackTrace();
            log.info("令牌不存在");
            Result error = Result.error("NOT_LOGIN");
            String notlogin = JSONObject.toJSONString(error);
            sqon.getWriter().write(notlogin);
            return;
        }
        //6.放行
        log.info("你在呢！放行");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
