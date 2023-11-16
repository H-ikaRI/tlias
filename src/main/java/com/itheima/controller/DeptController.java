package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

/**
 * ClassName: DeptController
 * Package: com.itheima.controller
 * Description:
 *
 * @Author: Hjr
 * @Create 2023/10/30 21:32
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping("/depts")
public class DeptController {

    //private static Logger log = LoggerFactory.getLogger(DeptController.class);
    @Autowired
    private DeptService deptService;
    //查询部门数据
    @RequestMapping
    public Result list(){

            log.info("查询全部部门数据");
            //调用service查询部门数据
        List<Dept> deptList = deptService.list();

        return Result.success(deptList);
    }

    //删除部门
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除部门数据{}",id);
        //调用service删除部门
        deptService.delete(id);
        return Result.success();
    }

    //新增部门
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("新增部门,{}",dept);
        //调用service
        deptService.add(dept);
        return Result.success();
    }

    //根据id查询部门
    @GetMapping("/{id}")
    public Result ids(@PathVariable Integer id){
        log.info("根据id查询部门{}",id);
        Dept dept = deptService.ids(id);
        return Result.success(dept);
    }

    //根据id修改部门
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("跟据id修改部门{}",dept);
        deptService.update(dept);

        return Result.success();
    }


}
