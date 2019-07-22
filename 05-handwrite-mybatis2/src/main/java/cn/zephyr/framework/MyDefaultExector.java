package cn.zephyr.framework;


/**
 * @Author: laizonghao
 * @Description: 默认执行器：执行sql、结果映射
 * @Date: 2019-07-15 11:42
 */
public class MyDefaultExector implements MyExecutor {

    private MyConfiguration configuration;

    @Override
    public <T> T query(MapperRegistery.MapperDate<T> mapperDate , String parameter) {
        MyStatementHandler statementHandler = new MyStatementHandler(configuration);
        return statementHandler.query(mapperDate,parameter);
    }
}
