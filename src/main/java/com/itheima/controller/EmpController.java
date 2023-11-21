package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import com.itheima.service.EmpService;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * ClassName: EmpController
 * Package: com.itheima.controller
 * Description:
 *
 * @Author: Hjr
 * @Create 2023/10/30 21:34
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;
    //分页展示员工
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end){
        log.info("分页查询，参数：{},{},{},{},{},{}",page,pageSize,name,gender,begin,end);
        //调用service方法进行分页查询
        PageBean pagebean= empService.page(page,pageSize,name,gender,begin,end);
        return Result.success(pagebean);
    }
    //删除员工
    @DeleteMapping("/{ids}")
    @Log
    public Result delete(@PathVariable List<Integer> ids){
        log.info("删除所选员工:{}",ids);
        empService.delete(ids);
        return Result.success();
    }

    //新增员工
    @RequestMapping
    @Log
    public Result save(@RequestBody Emp emp){
        log.info("新增员工信息,{}", emp);
        empService.save(emp);
        return Result.success();
    }

    //获得员工的id
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("输入的id为{}",id);
        Emp emp = empService.getById(id);

        return Result.success(emp);
    }

    //根据id修改员工
    @PutMapping()
    @Log
    public Result upload(@RequestBody Emp emp){
        log.info("更新的对象是{}",emp);
        empService.update(emp);
        return Result.success();
    }



}
