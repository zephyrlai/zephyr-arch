package cn.zephyr.reflection;

import java.lang.reflect.Constructor;

/**
 * @ClassName: ReflectionDemo
 * @Author: laizonghao
 * @Description: 反射Demo
 * @Date: 2019-08-09 16:24
 */
public class ReflectionDemo {
    public static void main(String[] args) throws Exception {
        Class<DemoEntity> clazz = (Class<DemoEntity>) Class.forName("cn.zephyr.reflection.DemoEntity");
        System.err.println(getInstance01(clazz));
        System.err.println(getInstance02(clazz));
    }

    private static DemoEntity getInstance01(Class<DemoEntity> clazz) throws Exception {
        // 使用无参构造器
        DemoEntity demoEntity = clazz.newInstance();
        demoEntity.setId(1);
        demoEntity.setName("哈哈");
        return demoEntity;
    }
    private static DemoEntity getInstance02(Class<DemoEntity> clazz) throws Exception {
        // 指定（有参）构造器
        Constructor<DemoEntity> constructor = clazz.getDeclaredConstructor(Integer.class, String.class);
        return constructor.newInstance(2, "呵呵");
    }
}
