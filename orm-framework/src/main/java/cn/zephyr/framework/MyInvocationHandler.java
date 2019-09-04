package cn.zephyr.framework;

import cn.zephyr.annotation.MyInsert;
import cn.zephyr.annotation.MyParam;
import cn.zephyr.annotation.MySelect;
import cn.zephyr.utils.JDBCUtils;
import cn.zephyr.utils.SQLUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
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

    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MySelect mySelect = method.getDeclaredAnnotation(MySelect.class);
        if(null != mySelect){
            System.err.println("===execute sql select===");
        }
        MyInsert myInsert = method.getDeclaredAnnotation(MyInsert.class);
        if(null != myInsert){
            // 存放入参键、值
            Map<String,Object> paramMap = new HashMap<>();
            System.err.println("===execute sql insert===");
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
        return null;
    }
}
