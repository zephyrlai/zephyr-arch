package cn.zephyr.framework;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-07-13 17:23
 */
public class MyConfiguration {

    public <T> T getMapper(Class<T> clazz,MySqlSession sqlSession){
        return (T)Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                new Class[]{clazz},
                new MyMapperProxy(sqlSession));
    }

    /**
     * 模拟解析完成的mapper.xml(获取nameSpace、sql语句)
     */
    static class TestMapperXml{
        private static final String nameSpace="cn.zephyr.demo.dao.SysUserMapper";

        private static final Map<String,String> sqlMap = new HashMap<String, String>();

        /**
         * 模拟初始化加载
         */
        static {
            sqlMap.put("selectByPrimaryKey","select * from sys_user where id = ?");
        }

        public static String getNameSpace() {
            return nameSpace;
        }

        public static Map<String, String> getSqlMap() {
            return sqlMap;
        }
    }
}
