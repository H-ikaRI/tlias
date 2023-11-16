package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

/**
 * ClassName: EmpMapper
 * Package: com.itheima.mapper
 * Description:
 *
 * @Author: Hjr
 * @Create 2023/10/30 21:34
 * @Version 1.0
 */
@Mapper
public interface EmpMapper {
    @Select("select * from emp where id = #{id}")
    Emp getById(Integer id);

 /* //查询总记录数
     @Select("select count(*) from emp")
     public long count();
     //分页查询获取列表数据
     @Select("select * from emp limit #{start},#{page}")
     public List<Emp> page(Integer start,Integer page);
 */
    //员工信息的查询
    /*@Select("select * from emp")*/
    public List<Emp> list(String name, Short gender,
                          LocalDate begin, LocalDate end);

    //批量删除
    void delete(List<Integer> ids);

    //新增员工
    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time)"+
            "values (#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);


    void update(Emp emp);

    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getByUsernameAndPassword(Emp emp);
    
    //根据部门删除员工
    @Delete("delete  from emp where dept_id = #{deptId}")
    void deleteEmp(Integer deptId);
}
