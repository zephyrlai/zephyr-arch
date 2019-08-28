package cn.zephyr.framework;

import cn.zephyr.annotation.MyAutowire;
import cn.zephyr.annotation.MyService;
import cn.zephyr.util.ClassUtil;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: MyApplicationContext
 * @Author: laizonghao
 * @Description:
 * @Date: 2019/8/28 11:32
 */
public class MyApplicationContext {
    /**
     * 包路径
     */
    private String packagePath;

    private static ConcurrentHashMap<String,Object> beanMap = new ConcurrentHashMap<>();

    public MyApplicationContext(String packagePath) {
        this.packagePath = packagePath;
        initContext();
        dependenceInject();
    }

    // Bean扫描
    public void initContext(){
        try {
            List<Class<?>> classes = ClassUtil.getClasses(packagePath);
            if(!CollectionUtils.isEmpty(classes)){
                for (Class<?> clazz : classes) {
                    MyService myService = clazz.getDeclaredAnnotation(MyService.class);
                    if(null == myService)
                        continue;
                    Object beanObj = clazz.newInstance();
                    String beanId = toLowerCaseFirstOne(clazz.getSimpleName());
                    beanMap.put(beanId,beanObj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 依赖注入
    public void dependenceInject(){
        try {
            if(!CollectionUtils.isEmpty(beanMap)){
                for (Map.Entry<String, Object> entryObj : beanMap.entrySet()) {
                    Field[] declaredFields = entryObj.getValue().getClass().getDeclaredFields();
                    if(declaredFields.length>0){
                        for (Field field : declaredFields) {
                            if(null ==field.getDeclaredAnnotation(MyAutowire.class))
                                continue;
                            field.setAccessible(true);
                            Object fieldObj = beanMap.get(field.getName());
                            if(null != fieldObj)
                                field.set(entryObj.getValue(),fieldObj);
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public Object getBean(String beanName){
        return beanMap.get(beanName);
    }

    // 首字母转小写
    private String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }
}
