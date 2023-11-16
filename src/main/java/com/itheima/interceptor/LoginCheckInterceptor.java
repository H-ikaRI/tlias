package com.itheima.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * ClassName: LoginCheckInterceptor
 * Package: com.itheima.interceptor
 * Description:
 *
 * @Author: Hjr
 * @Create 2023/11/9 20:12
 * @Version 1.0
 */
@Component
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override//目标资源方法运行前运行，true放行
    public boolean preHandle(HttpServletRequest sre, HttpServletResponse spon, Object handler) throws Exception {
        //1.获取url
        String url = sre.getRequestURL().toString();
        log.info("获取的url为{}",url);
        //2.判断url是否有login
        if(url.contains("login")){
            log.info("直接放行");
            return true;
        }
        //3.获取请求头中的令牌
        String jwt = sre.getHeader("token");
        //4.判断令牌是否存在
        if(!StringUtils.hasLength(jwt)){
            log.info("令牌不存在");
            Result error = Result.error("NOT_LOGIN");
            String notlogin = JSONObject.toJSONString(error);
            spon.getWriter().write(notlogin);
            return false;
        }
        //5.解析token
        try {
            JwtUtils.parseJWT(jwt);
        }catch (Exception e){
            e.printStackTrace();
            log.info("令牌不存在");
            Result error = Result.error("NOT_LOGIN");
            String notlogin = JSONObject.toJSONString(error);
            spon.getWriter().write(notlogin);
            return false;
        }
        //6.放行
        log.info("你在呢！放行");
        return true;
    }

    @Override//目标资源方法运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("post");
    }

    @Override//视图渲染完毕后运行，最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("after");
    }
}
