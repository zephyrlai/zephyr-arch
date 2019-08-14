package proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @ClassName: CglibProxy
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-14 23:40
 */
public class CglibProxy implements MethodInterceptor {

    private Object targetObject;

    public Object getInstance(Object target) {
        // 设置需要创建子类的类
        this.targetObject = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }


    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        Object result;
        System.err.println("==>开启数据库事务");
        result = method.invoke(targetObject, args);
        System.err.println("<==关闭数据库事务");
        return result;
    }
}
