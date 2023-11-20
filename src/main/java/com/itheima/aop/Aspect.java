package com.itheima.aop;

import com.alibaba.fastjson.JSONObject;
import com.itheima.anno.Log;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Target;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * ClassName: Aspect
 * Package: com.itheima.aop
 * Description:
 *
 * @Author: Hjr
 * @Create 2023/11/20 19:01
 * @Version 1.0
 */
@Slf4j
@Component
@org.aspectj.lang.annotation.Aspect
public class Aspect {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.itheima.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {

        //操作人id
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);
        Integer OperatorId = (Integer) claims.get("id");
        //操作时间
        LocalDateTime now = LocalDateTime.now();
        //操作类名
        String ClassName = joinPoint.getTarget().getClass().getName();
        //操作方法名
        String mName = joinPoint.getSignature().getName();
        //操作方法参数
        Object[] args = joinPoint.getArgs();
        String argsString = Arrays.toString(args);
        //开始时间
        long begin = System.currentTimeMillis();
        //按原始方法运行
        Object result = joinPoint.proceed();
        //结束时间
        long end = System.currentTimeMillis();
        //操作方法返回值
        String MenthodReturn = JSONObject.toJSONString(result);
        //操作耗时
        long time = end - begin;
        //插入数据库
        OperateLog operateLog = new OperateLog(null,OperatorId,now,ClassName,mName,argsString,MenthodReturn,time);
        operateLogMapper.insert(operateLog);
        log.info("aop记录操作日志；{}",operateLog);
        return result;
    }
}
