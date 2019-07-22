package cn.zephyr.framework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-07-13 17:51
 */
public class MyMapperProxy implements InvocationHandler {
    /**
     * MapperProxy是为了拿到sql，所以实际的sql执行需要SqlSession来完成
     */
    private MySqlSession sqlSession;

//    private Class<T> mapperInterface;

    public MyMapperProxy(MySqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MapperRegistery.MapperDate mapperDate = sqlSession.getConfiguration().getMapperRegistery().get(method.getDeclaringClass() + "." + method.getName());
        if(null != mapperDate){
            return sqlSession.selectOne(mapperDate,String.valueOf(args[0]));
        }
        return method.invoke(proxy,args);
        /*if(method.getDeclaringClass().getCanonicalName().equals(MyConfiguration.TestMapperXml.getNameSpace())){
            String sql = MyConfiguration.TestMapperXml.getSqlMap().get(method.getName());
            return sqlSession.selectOne(sql,String.valueOf(args[0]));
        }
        return method.invoke(args);*/
    }
}
