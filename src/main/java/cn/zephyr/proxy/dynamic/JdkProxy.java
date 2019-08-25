package cn.zephyr.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName: JdkProxy
 * @Author: laizonghao
 * @Description: JDK动态代理
 * @Date: 2019/8/24 17:16
 */
public class JdkProxy implements InvocationHandler {

    private Object target;

    public JdkProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.err.println("==开启数据库事务==");
        Object result = method.invoke(target, args);
        Thread.sleep(10);
        System.err.println("==提交数据库事务==");
        return result;
    }
}
