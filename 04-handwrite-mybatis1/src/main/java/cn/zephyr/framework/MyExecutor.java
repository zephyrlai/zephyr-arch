package cn.zephyr.framework;

/**
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-07-13 17:23
 */
public interface MyExecutor {

    <T> T query(String statement, String parameter);
}
