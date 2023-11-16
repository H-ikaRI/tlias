package com.itheima.service;

import com.itheima.pojo.Dept;
//import jdk.incubator.foreign.Addressable;

import java.util.List;

/**
 * 部门管理
 */
public interface DeptService {
    //查询全部部门数据
    List<Dept> list();

    //删除部门
    void delete(Integer id);
    //新增部门
    void add(Dept dept);
    //根据id查询部门
    Dept ids(Integer id);
    //根据id修改部门
    void update(Dept dept);
}
