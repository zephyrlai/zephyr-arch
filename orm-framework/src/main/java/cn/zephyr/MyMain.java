package cn.zephyr;

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
        userMapper.insertUser("haha",25);
    }
}
