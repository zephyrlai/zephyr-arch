package cn.zephyr.singleton;

/**
 * @ClassName: LazySingletonDemo
 * @Author: laizonghao
 * @Description: 懒汉式单例demo（无法防止反射机制的漏洞）
 * @Date: 2019-08-10 16:39
 */
public class LazySingletonDemo {
    private static LazySingletonDemo lazySingletonDemo;

    private LazySingletonDemo() {
    }

    public static synchronized LazySingletonDemo newInstance(){
        if(lazySingletonDemo == null)
            lazySingletonDemo = new LazySingletonDemo();
        return lazySingletonDemo;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.err.println(LazySingletonDemo.newInstance());
                }
            }).start();
        }
    }
}
