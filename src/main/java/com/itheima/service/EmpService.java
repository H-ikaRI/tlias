package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理
 */
public interface EmpService {
    Emp getById(Integer id);
    //删除所选员工
    void delete(List<Integer> ids);


    //查询所所选员工
    PageBean page(Integer page, Integer pageSize, String name, Short gender,
                  LocalDate begin, LocalDate end);

    //新增员工
    void save(Emp emp);

    void update(Emp emp);

    Emp login(Emp emp);
}
