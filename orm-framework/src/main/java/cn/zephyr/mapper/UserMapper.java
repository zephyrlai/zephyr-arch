package cn.zephyr.mapper;

import cn.zephyr.annotation.MyInsert;
import cn.zephyr.annotation.MyParam;
import cn.zephyr.annotation.MySelect;
import cn.zephyr.entity.SysUser;

/**
 * @ClassName: UserMapper
 * @Author: laizonghao
 * @Description:
 * @Date: 2019/9/4 14:13
 */
public interface UserMapper {
    @MyInsert("insert into sys_user(username,age) values(#{userName},#{age})")
    int insertUser(@MyParam("userName")String userName, @MyParam("age")Integer age);

    @MySelect("select * from sys_user where id = #{id}")
    SysUser queryByPrimaryKey(@MyParam("id") Integer id);
}
