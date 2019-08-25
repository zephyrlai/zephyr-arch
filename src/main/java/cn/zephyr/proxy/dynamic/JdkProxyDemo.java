package cn.zephyr.proxy.dynamic;

import cn.zephyr.proxy.service.UserService;
import cn.zephyr.proxy.service.impl.UserServiceImpl;

import java.lang.reflect.Proxy;

/**
 * @ClassName: JdkProxyDemo
 * @Author: laizonghao
 * @Description:
 * @Date: 2019/8/24 17:25
 */
public class JdkProxyDemo {
    public static void main(String[] args) {
        JdkProxy jdkProxy = new JdkProxy(new UserServiceImpl());
        Class<?>[] interfaces = UserServiceImpl.class.getInterfaces();
        ClassLoader classLoader = UserServiceImpl.class.getClassLoader();
        UserService userService = (UserService) Proxy.newProxyInstance(classLoader, interfaces, jdkProxy);
        userService.insert(new Object());
    }
}
