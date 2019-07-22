package cn.zephyr.framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @Author: laizonghao
 * @Description: JDBC Statement 处理器
 * @Date: 2019-07-22 17:51
 */
public class MyStatementHandler {


    private MyResultSetHandler myResultSetHandler;

    public MyStatementHandler(MyConfiguration myConfiguration) {
        this.myResultSetHandler = new MyResultSetHandler(myConfiguration);
    }

    public <T> T query(MapperRegistery.MapperDate<T> mapperDate, Object parameter){
        Connection connection = getConnection();
        try {
            if(null == connection)
                throw new RuntimeException("get sql connection failure.");
            // todo 硬编码：模拟ParamaterHandler实现sql与参数的组装
            PreparedStatement preparedStatement = connection.prepareStatement(mapperDate.getSql());
            preparedStatement.setInt(1,Integer.parseInt(String.valueOf(parameter)));
            ResultSet resultSet = preparedStatement.executeQuery();
            return myResultSetHandler.handler(resultSet,mapperDate.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


    /**
     * 获取连接
     * @return
     */
    private Connection getConnection(){
        String url = "jdbc:mysql://localhost:3306/mybatis_test?useSSL=false";
        String user = "root";
        String password = "111111";
        Connection connection = null;
        try {
            //1.加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            //2. 获得数据库连接
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
