package cn.zephyr;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName: PoolManager
 * @Author: laizonghao
 * @Description:
 * @Date: 2019/8/29 16:59
 */
public class PoolManager {

    private MyDbConnectionPool connectionPool = new MyDbConnectionPool();

    public Connection getConnection() throws Exception {
        return connectionPool.getConnection();
    }

    public void releaseConection(Connection connection) throws SQLException {
        connectionPool.releaseConnection(connection);
    }

}
