package cn.zephyr.proxydemo;

import java.lang.reflect.Proxy;

/**
 * @ClassName: MyProxyMain
 * @Author: laizonghao
 * @Description:
 * @Date: 2019/9/4 11:26
 */
public class MyProxyMain {

    public static void main(String[] args) {
        UserMapper userMapper = (UserMapper) Proxy.newProxyInstance(
                UserMapper.class.getClassLoader(),
                new Class[]{UserMapper.class},
                new MyInvocationHandler(UserMapper.class)
        );
        int i = userMapper.insertSelective();
        System.err.println("sql返回结果："+i);
    }

}
