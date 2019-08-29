package cn.zephyr;

import lombok.Getter;

/**
 * @ClassName: PoolConfiguration
 * @Author: laizonghao
 * @Description: 模拟连接池配置
 * @Date: 2019/8/29 16:00
 */
public class PoolConfiguration {

    public static final String driverName="com.mysql.cj.jdbc.Driver";
    public static final String url="jdbc:mysql://localhost:3306/zephyr_db";
    public static final String username="root";
    public static final String password="111111";

    // 空闲连接数
    public static final Integer freeConnectionNum=4;
    // 最大连接数
    public static final Integer totalConnectionNum=5;
    // 失败重试次数
    public static final Integer retryTimes=5;
    // 重试间隔时间
    public static final Integer secondsBetweenRetry = 1;
}
