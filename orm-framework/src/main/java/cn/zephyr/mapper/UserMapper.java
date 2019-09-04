package cn.zephyr.mapper;

import cn.zephyr.annotation.MyInsert;
import cn.zephyr.annotation.MyParam;

/**
 * @ClassName: UserMapper
 * @Author: laizonghao
 * @Description:
 * @Date: 2019/9/4 14:13
 */
public interface UserMapper {
    @MyInsert("insert into sys_user(username,age) values(#{userName},#{age})")
    int insertUser(@MyParam("userName")String userName, @MyParam("age")Integer age);
}
