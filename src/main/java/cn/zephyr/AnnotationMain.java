package cn.zephyr;

import java.lang.reflect.Method;

/**
 * @ClassName: AnnotationMain
 * @Author: laizonghao
 * @Description:
 * @Date: 2019/8/26 20:08
 */
public class AnnotationMain {
    public static void main(String[] args) throws NoSuchMethodException {
        Method testMethod = AnnotationMain.class.getDeclaredMethod("testMethod");
        MyAnnotation annotation = testMethod.getAnnotation(MyAnnotation.class);
        System.err.println(annotation.value());
        System.err.println(annotation.name());
    }

    @MyAnnotation(value = 123,name = "哈哈")
    public void testMethod(){};
}
