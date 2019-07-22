package cn.zephyr.framework;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName: MyResultSetHandler
 * @Author: laizonghao
 * @Description: 结果映射处理类
 * @Date: 2019-07-21 16:32
 */
public class MyResultSetHandler {

    private MyConfiguration myConfiguration;

    public MyResultSetHandler(MyConfiguration myConfiguration) {
        this.myConfiguration = myConfiguration;
    }

    public <T> T handler(ResultSet rs,Class<T> resultClass){
        try {
            // 最终的返回对象，需要提供无参构造
            T resultObj = resultClass.newInstance();
            // todo 只处理了第一条结果
            if(rs.next()){
                for (Field field : resultObj.getClass().getDeclaredFields()) {
                    // 拿到setter方法
                    Method setterMethod = resultObj.getClass().getMethod("set" + upperCaptial(field.getName()), field.getType());
                    setterMethod.invoke(resultObj,getResult(field,rs));
                }
            }
            return resultObj;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private String upperCaptial(String name){
        return name.substring(0,1).toUpperCase()+name.substring(1);
    }

    // todo 模拟TypeHandler
    private Object getResult(Field field, ResultSet rs) throws SQLException {
        if(field.getType() == Integer.class){
            return rs.getInt(field.getName());
        }else if(field.getType() == String.class){
            return rs.getString(field.getName());
        }else
            return null;
    }


}
