package cn.zephyr.singleton;

/**
 * @ClassName: HungerySingleTonDemo
 * @Author: laizonghao
 * @Description: 饿汉式单例demo（无法防止反射机制的漏洞）
 * @Date: 2019-08-09 17:03
 */
public class HungrySingletonDemo {
    private static HungrySingletonDemo singleTonDemo = new HungrySingletonDemo();

    public static HungrySingletonDemo getInstance(){
        return singleTonDemo;
    }

    private HungrySingletonDemo() {
        System.err.println("==调用构造器==");
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("程序开始执行==>");
        Thread.sleep(100);
        System.err.println(HungrySingletonDemo.getInstance());
        System.err.println(HungrySingletonDemo.getInstance());
        System.out.println("程序结束执行<==");
    }
}
