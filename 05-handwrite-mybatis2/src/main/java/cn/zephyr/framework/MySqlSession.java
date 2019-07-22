package cn.zephyr.framework;

import lombok.Data;

/**
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-07-13 17:22
 */
@Data
public class MySqlSession {

    private MyExecutor executor;

    private MyConfiguration configuration;

    public  MySqlSession(MyConfiguration configuration,MyExecutor executor ) {
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
        return configuration.getMapper(clazz,this);
    }

    public <T> T selectOne(MapperRegistery.MapperDate<T> mapperDate,String parameter){
        return executor.query(mapperDate,parameter);
    }
    
}
