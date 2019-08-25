package cn.zephyr.proxy.staticproxy;

import cn.zephyr.proxy.service.UserService;

/**
 * @ClassName: UserServiceProxy
 * @Author: laizonghao
 * @Description: 代理类
 * @Date: 2019/8/24 17:17
 */
public class UserServiceProxy implements UserService{

    private UserService target;

    public UserServiceProxy(UserService target) {
        this.target = target;
    }

    @Override
    public boolean insert(Object object) {
        System.err.println("===开启事务===");
        boolean result = target.insert(object);
        System.err.println("===提交事务===");
        return result;
    }

}
