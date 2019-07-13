package cn.zephyr.framework;

/**
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-07-13 17:22
 */
public class MySqlSession {

    private MyExecutor executor;

    private MyConfiguration configuration;

    public MySqlSession(MyExecutor executor, MyConfiguration configuration) {
        this.executor = executor;
        this.configuration = configuration;
    }

    /**
     * 根据接口类获取Mapper（动态代理）；
     * 交由configuration返回所需示例，体现了单一原则
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> clazz){
        return configuration.getMapper(clazz);
    }

    public <T> T selectOne(String statement,String parameter){
        return executor.query(statement,parameter);
    }
    
}
