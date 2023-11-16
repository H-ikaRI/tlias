package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: LoginController
 * Package: com.itheima.controller
 * Description:
 *
 * @Author: Hjr
 * @Create 2023/11/5 15:30
 * @Version 1.0
 */
@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("判断是否存在该用户:{}",emp);
        Emp e = empService.login(emp);

        if(e != null){
            Map<String,Object> cliams = new HashMap<>();
            cliams.put("name",e.getName());
            cliams.put("id",e.getId());
            cliams.put("username",e.getUsername());
            String jwt = JwtUtils.generateJwt(cliams);
            return Result.success(jwt);
        }
        return Result.error("用户名或密码错误");
    }
}
