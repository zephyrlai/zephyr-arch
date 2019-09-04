package cn.zephyr.proxydemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName: MyInvocationHandler
 * @Author: laizonghao
 * @Description:
 * @Date: 2019/9/4 11:27
 */
public class MyInvocationHandler implements InvocationHandler {

    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.err.println("==动态代理---方法拦截==");
        return 1;
    }
}
