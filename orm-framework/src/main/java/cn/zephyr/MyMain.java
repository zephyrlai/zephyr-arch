package cn.zephyr;

import cn.zephyr.entity.SysUser;
import cn.zephyr.framework.SqlSession;
import cn.zephyr.mapper.UserMapper;

/**
 * @ClassName: MyMain
 * @Author: laizonghao
 * @Description:
 * @Date: 2019/9/4 14:18
 */
public class MyMain {

    public static void main(String[] args) {
        UserMapper userMapper = SqlSession.getMapper(UserMapper.class);
        int insertId = userMapper.insertUser("haha", 25);
        System.err.println("insert自增id："+insertId);
        SysUser sysUser = userMapper.queryByPrimaryKey(4);
        System.err.println(sysUser.toString());
    }
}
