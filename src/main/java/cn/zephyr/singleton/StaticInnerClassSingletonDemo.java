package cn.zephyr.singleton;

/**
 * @ClassName: StaticInnerClassSingletonDemo
 * @Author: laizonghao
 * @Description: 静态内部类方式的单例demo（无法防止反射机制的漏洞）
 * @Date: 2019-08-10 16:45
 */
public class StaticInnerClassSingletonDemo {

    private static class MyInnerClass{
        private static StaticInnerClassSingletonDemo singletonDemo = new StaticInnerClassSingletonDemo();
    }

    private StaticInnerClassSingletonDemo() {
        System.err.println("==调用构造器==");
    }

    public static StaticInnerClassSingletonDemo getInstance(){
        return MyInnerClass.singletonDemo;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("程序开始执行==>");
        Thread.sleep(100);
        System.err.println(StaticInnerClassSingletonDemo.getInstance());
        System.err.println(StaticInnerClassSingletonDemo.getInstance());
        System.err.println(StaticInnerClassSingletonDemo.getInstance());
        System.out.println("程序结束执行<==");
    }
}
