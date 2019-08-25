package cn.zephyr.proxy.staticproxy;

import cn.zephyr.proxy.service.UserService;
import cn.zephyr.proxy.service.impl.UserServiceImpl;

/**
 * @ClassName: StaticProxyDemo
 * @Author: laizonghao
 * @Description: 静态代理demo
 * @Date: 2019/8/24 17:15
 */
public class StaticProxyDemo {
    public static void main(String[] args) {
        UserService userService = new UserServiceProxy(new UserServiceImpl());
        userService.insert(new Object());
    }
}
