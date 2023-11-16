package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName: PageBean
 * Package: com.itheima.pojo
 * Description:
 *分页查询结果的封装类
 * @Author: Hjr
 * @Create 2023/10/31 20:03
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class PageBean {
    private long total;//总记录数
    private List rows;//数据列表
}
