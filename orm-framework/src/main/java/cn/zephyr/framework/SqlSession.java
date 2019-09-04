package cn.zephyr.framework;

import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: SqlSession
 * @Author: laizonghao
 * @Description:
 * @Date: 2019/9/4 14:07
 */
public class SqlSession {

    public static <T> T getMapper(Class<T> clazz){
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(),new Class[]{clazz},new MyInvocationHandler(clazz));
    }
}
