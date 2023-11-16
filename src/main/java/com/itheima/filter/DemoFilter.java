package com.itheima.filter;

import jakarta.servlet.*;
import jakarta.servlet.Filter;
import jakarta.servlet.annotation.WebFilter;
import org.springframework.http.StreamingHttpOutputMessage;

import javax.imageio.spi.ServiceRegistry;
import java.io.IOException;

/**
 * ClassName: DemoFilter
 * Package: com.itheima.filter
 * Description:
 *
 * @Author: Hjr
 * @Create 2023/11/8 19:32
 * @Version 1.0
 */
//@WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("执行init方法");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("执行dofilter之前");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("执行dofilter之后");
    }

    @Override
    public void destroy() {
        System.out.println("执行destroy方法");
    }
}
