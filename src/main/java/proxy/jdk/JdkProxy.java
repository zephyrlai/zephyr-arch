package proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName: JdkProxy
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-14 23:40
 */
public class JdkProxy implements InvocationHandler {

    private BusiDao target;

    public JdkProxy(BusiDao target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result;
        System.err.println("==>开启数据库事务");
        result = method.invoke(target, args);
        System.err.println("<==关闭数据库事务");
        return result;
    }
}
