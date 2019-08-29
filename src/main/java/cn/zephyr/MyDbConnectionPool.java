package cn.zephyr;

import org.apache.commons.collections4.CollectionUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: MyDbConnectionPool
 * @Author: laizonghao
 * @Description:
 * @Date: 2019/8/29 16:07
 */
public class MyDbConnectionPool {

    private static final Object lock = new Object();
    // 空闲连接池
    private static List<Connection> freeConnectionList = new Vector<Connection>();
    // 活动连接池
    private static List<Connection> activeConnectionList = new Vector<Connection>();

    // 重试次数控制
    private AtomicInteger retryCounter = new AtomicInteger(0);

    static{
        init();
    }

    /**
     * 对外提供数据库连接
     */
    public Connection getConnection() throws Exception {
        synchronized (lock) {
            // 重试计数器自增
            retryCounter.incrementAndGet();
            // 空闲池中有，直接从空闲池中取
            if (!CollectionUtils.isEmpty(freeConnectionList)) {
                Connection connection = freeConnectionList.remove(0);
                activeConnectionList.add(connection);
                return connection;
            }
            // 空闲池中没有可用连接（现有连接已全部激活），若总数小于配置的连接总数，则立即创建新连接
            if (activeConnectionList.size() < PoolConfiguration.totalConnectionNum) {
                Connection connection = createConnection();
                activeConnectionList.add(connection);
                return connection;
            }
            // 重试次数是否已满
            if (retryCounter.intValue() >= PoolConfiguration.retryTimes) {
                throw new RuntimeException("get connection fail,already tried " + PoolConfiguration.retryTimes + "times.");
            }
            // 连接已全部创建并无空闲，则等待其他应用释放连接；
            System.err.println(Thread.currentThread().getName() + "-thread is going to retry " + retryCounter.intValue() + " time");
            lock.wait(PoolConfiguration.secondsBetweenRetry * 1000);
            return getConnection();
        }
    }

    /**
     * （对外提供）关闭连接
     * @param connection
     * @throws SQLException
     */
    public static void releaseConnection(Connection connection) throws SQLException {
        synchronized (lock){
            // 移出活动连接池
            activeConnectionList.remove(connection);
            // 如果空闲池没满，则丢入空闲池
            if(freeConnectionList.size()<PoolConfiguration.freeConnectionNum){
                freeConnectionList.add(connection);
            }else{
                // 否则关闭连接
                connection.close();
            }
            lock.notify();
        }
    }


    /**
     * 初始化连接池 <br>
     * 创建freeConnectionNum/2个连接，向下取整
     */
    private static void init(){
        try {
            for (Integer i = 0; i < PoolConfiguration.freeConnectionNum/2; i++) {
                Connection connection = createConnection();
                freeConnectionList.add(connection);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 新建连接
     * @return
     * @throws Exception
     */
    private static Connection createConnection() throws Exception {
        Class.forName(PoolConfiguration.driverName);
        return DriverManager.getConnection(PoolConfiguration.url, PoolConfiguration.username, PoolConfiguration.password);
    }


}
