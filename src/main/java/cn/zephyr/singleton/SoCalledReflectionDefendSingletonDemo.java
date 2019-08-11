package cn.zephyr.singleton;

import java.lang.reflect.Field;

/**
 * @ClassName: SoCalledReflectionDefendSingletonDemo
 * @Author: laizonghao
 * @Description: 网传所谓防反射机制的单例
 * @Date: 2019-08-11 16:36
 */
public class SoCalledReflectionDefendSingletonDemo {
    private static Boolean flag = false;

    private static SoCalledReflectionDefendSingletonDemo singletonDemo;

    private SoCalledReflectionDefendSingletonDemo() {
        if(flag){
            throw new RuntimeException("Reflection Defend!");
        }
    }

    public static synchronized SoCalledReflectionDefendSingletonDemo getInstance(){
        if(null == singletonDemo){
            singletonDemo = new SoCalledReflectionDefendSingletonDemo();
            flag = true;
        }
        return singletonDemo;
    }

    public static void main(String[] args) throws Exception {
        SoCalledReflectionDefendSingletonDemo instance = SoCalledReflectionDefendSingletonDemo.getInstance();
        System.err.println(instance);
        System.err.println(SoCalledReflectionDefendSingletonDemo.getInstance());
        System.err.println(SoCalledReflectionDefendSingletonDemo.getInstance());
        // 反射攻击开始==>
        Field flag = SoCalledReflectionDefendSingletonDemo.class.getDeclaredField("flag");
        flag.setAccessible(true);
        flag.set(instance,false);
        Field singletonDemo = SoCalledReflectionDefendSingletonDemo.class.getDeclaredField("singletonDemo");
        singletonDemo.set(instance,null);
        // 反射攻击结束<==
        System.out.println(SoCalledReflectionDefendSingletonDemo.getInstance());
    }
}
