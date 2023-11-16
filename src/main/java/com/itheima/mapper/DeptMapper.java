package com.itheima.mapper;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * ClassName: DeptMapper
 * Package: com.itheima.mapper
 * Description:
 *
 * @Author: Hjr
 * @Create 2023/10/30 21:34
 * @Version 1.0
 */
@Mapper
public interface DeptMapper {
    //查询全部部门数据
    @Select("select * from dept")
    List<Dept> list();

    //根据id删除数据
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    //新增部门语句
    @Insert("insert into dept (name, create_time, update_time) values (#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    //根据id查询部门
    @Select("select * from dept where id = #{id}")
    Dept ids(Integer id);

    //根据id更新部门
    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);

}
