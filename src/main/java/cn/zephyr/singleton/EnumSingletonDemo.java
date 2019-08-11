package cn.zephyr.singleton;

import java.lang.reflect.Field;

/**
 * @ClassName: EnumSingletonDemo
 * @Author: laizonghao
 * @Description: 基于枚举的单例(Enum天生可防反射攻击)
 * @Date: 2019-08-10 17:18
 */
public enum  EnumSingletonDemo {
    INSTANCE;

    private String name;
    private Integer age;

    EnumSingletonDemo() {
        System.err.println("==执行构造器==");
    }

    public static void main(String[] args) throws InterruptedException {
        System.err.println("程序开始执行==>");
        Thread.sleep(100);
        for (int i = 0; i < 5; i++) {
            System.err.println(EnumSingletonDemo.INSTANCE.hashCode());
        }
    }

    /*public static void main(String[] args) throws Exception {
        EnumSingletonDemo instance = EnumSingletonDemo.INSTANCE;
        System.err.println(instance.hashCode());
        Field field = EnumSingletonDemo.class.getDeclaredField("INSTANCE");
        field.setAccessible(true);
        field.set(instance,null);
        System.err.println(EnumSingletonDemo.INSTANCE);
    }*/

}


