package cn.zephyr.framework;

import cn.zephyr.annotation.MyController;
import cn.zephyr.annotation.MyRequestMapping;
import cn.zephyr.utils.ClassUtil;
import org.apache.commons.collections4.CollectionUtils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: MyApplicationContext
 * @Author: laizonghao
 * @Description:
 * @Date: 2019/8/28 17:25
 */
public class MyApplicationContext {
    /**
     * 扫包路径
     */
    private String packagePath;

    // IOC容器
    private static ConcurrentHashMap<String,Object> beanMap = new ConcurrentHashMap<String, Object>();
    // url-obj映射
    private static ConcurrentHashMap<String,Object> urlBeanObjMap = new ConcurrentHashMap<>();
    // url-methodName 映射
    private static ConcurrentHashMap<String,String> urlMethodMap = new ConcurrentHashMap<>();

    public MyApplicationContext(String packagePath) {
        this.packagePath = packagePath;
        initBean();
        injectDependency();
        handlerMapping();
    }

    private void initBean(){
        try {
            List<Class<?>> classes = ClassUtil.getClasses(packagePath);
            if(CollectionUtils.isEmpty(classes))
                return;
            for (Class<?> clazz : classes) {
                MyController myController = clazz.getDeclaredAnnotation(MyController.class);
                if(null == myController)
                    continue;
                beanMap.put(toLowerCaseFirstOne(clazz.getSimpleName()),clazz.newInstance());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void injectDependency(){
        // todo
        /*if(beanMap.entrySet().size()>0){
            for (Map.Entry<String, Object> entry : beanMap.entrySet()) {
                Object beanObj = entry.getValue();

            }
        }*/
    }

    private void handlerMapping(){
        if(beanMap.entrySet().size()>0){
            for (Map.Entry<String, Object> entry : beanMap.entrySet()) {
                Object beanObj = entry.getValue();
                MyRequestMapping myRequestMapping = beanObj.getClass().getDeclaredAnnotation(MyRequestMapping.class);
                String prefixUrl = "";
                if(null != myRequestMapping)
                    prefixUrl = myRequestMapping.value();
                Method[] declaredMethods = beanObj.getClass().getDeclaredMethods();
                if(declaredMethods.length>0){
                    for (Method method : declaredMethods) {
                        MyRequestMapping requestMapping = method.getDeclaredAnnotation(MyRequestMapping.class);
                        if(requestMapping == null)
                            continue;
                        String urlTail = requestMapping.value();
                        urlBeanObjMap.put(prefixUrl+urlTail,beanObj);
                        urlMethodMap.put(prefixUrl+urlTail,method.getName());
                    }
                }
            }
        }
    }

    public Object getObjByUrl(String url){
        return urlBeanObjMap.get(url);
    }

    public String getMethodNameByUrl(String url){
        return urlMethodMap.get(url);
    }

    public Object getObjByBeanId(String beanId){
        return beanMap.get(beanId);
    }



    // 首字母转小写
    private String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }
}
