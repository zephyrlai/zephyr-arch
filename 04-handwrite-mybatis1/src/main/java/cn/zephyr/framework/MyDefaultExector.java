package cn.zephyr.framework;

import java.sql.*;

/**
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-07-15 11:42
 */
public class MyDefaultExector implements MyExecutor {
    @Override
    public <T> T query(String statement, String parameter) {
        String url = "jdbc:mysql://localhost:3306/mybatis_test?useSSL=false";
        String user = "root";
        String password = "111111";
        //1.加载驱动程序
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //2. 获得数据库连接
            Connection conn = DriverManager.getConnection(url, user, password);
            //3.操作数据库，实现增删改查
            PreparedStatement stmt = conn.prepareStatement(statement);
            stmt.setInt(1,Integer.parseInt(parameter));
            ResultSet rs = stmt.executeQuery();
            rs.next();
            SysUser sysUser = new SysUser();
            sysUser.setId(rs.getInt("id"));
            sysUser.setUsername(rs.getString("username"));
            sysUser.setPassword(rs.getString("password"));
            rs.close();
            stmt.close();
            conn.close();
            return (T)sysUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
