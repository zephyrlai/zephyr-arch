package cn.zephyr.framework;

import cn.zephyr.annotation.MyInsert;
import cn.zephyr.annotation.MyParam;
import cn.zephyr.annotation.MySelect;
import cn.zephyr.utils.JDBCUtils;
import cn.zephyr.utils.SQLUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: MyInvocationHandler
 * @Author: laizonghao
 * @Description:
 * @Date: 2019/9/4 11:56
 */
public class MyInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MySelect mySelect = method.getDeclaredAnnotation(MySelect.class);
        if(null != mySelect){
            return executeSelect(method, args, mySelect);
        }
        MyInsert myInsert = method.getDeclaredAnnotation(MyInsert.class);
        if(null != myInsert){
            return executeInsert(method, args, myInsert);
        }
        return null;
    }

    private Object executeSelect(Method method, Object[] args, MySelect mySelect) throws SQLException, InstantiationException, IllegalAccessException {
        System.err.println("===execute sql select===");
        // 存放入参键、值
        Map<String,Object> paramMap = new HashMap<>();
        // 获取注解上的原始sql
        String selectSql = mySelect.value();
        // 获取注解上原始sql的参数(包裹在'#{'、'}'之间)
        List<String> selectParameterList = SQLUtils.sqlSelectParameter(selectSql);
        // 获取方法入参名称
        Parameter[] parameters = method.getParameters();
        if(parameters.length>0){
            for (int i = 0; i < parameters.length; i++) {
                Parameter param = parameters[i];
                MyParam myParam = param.getDeclaredAnnotation(MyParam.class);
                String paramName = myParam.value();
                paramMap.put(paramName, args[i]);
            }
        }
        // 顺序组装jdbc sql语句的参数
        List<Object> paramObjList = new ArrayList<>();
        for (String selectParam : selectParameterList) {
            Object paramObj = paramMap.get(selectParam.trim());
            if(null == paramObj)
                throw new RuntimeException(selectParam+"参数不存在");
            paramObjList.add(paramObj);
        }

        selectSql = SQLUtils.parameQuestion(selectSql, selectParameterList);
        ResultSet rs = JDBCUtils.query(selectSql, paramObjList);
        if(!rs.next()){
            return null;
        }
        rs.previous();
        Class<?> returnType = method.getReturnType();
        Object newInstance = returnType.newInstance();
        while(rs.next()){
            Field[] fields = returnType.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                field.set(newInstance,rs.getObject(field.getName()));
            }
        }
        return newInstance;
    }

    private Object executeInsert(Method method, Object[] args, MyInsert myInsert) {
        System.err.println("===execute sql insert===");
        // 存放入参键、值
        Map<String,Object> paramMap = new HashMap<>();
        // 获取注解上的原始sql
        String insertSql = myInsert.value();
        // 获取注解上原始sql的参数(包裹在'#{'、'}'之间)
        String[] insertParameters = SQLUtils.sqlInsertParameter(insertSql);
        // 获取方法入参名称
        Parameter[] parameters = method.getParameters();
        if(parameters.length>0){
            for (int i = 0; i < parameters.length; i++) {
                Parameter param = parameters[i];
                MyParam myParam = param.getDeclaredAnnotation(MyParam.class);
                String paramName = myParam.value();
                paramMap.put(paramName, args[i]);
            }
        }
        // 顺序组装jdbc sql语句的参数
        List<Object> paramObjList = new ArrayList<>();
        for (String insertParameter : insertParameters) {
            Object paramObj = paramMap.get(insertParameter.trim());
            if(null == paramObj)
                throw new RuntimeException(insertParameter+"参数不存在");
            paramObjList.add(paramObj);
        }
        insertSql = SQLUtils.parameQuestion(insertSql, insertParameters);
        return JDBCUtils.insert(insertSql,true,paramObjList);
    }
}
