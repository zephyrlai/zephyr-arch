package cn.zephyr.singleton;

/**
 * @ClassName: DoubleCheckLockSingletonDemo
 * @Author: laizonghao
 * @Description: 双重锁检测机制下的单例
 * @Date: 2019-08-11 16:23
 */
public class DoubleCheckLockSingletonDemo {

    private static volatile DoubleCheckLockSingletonDemo singletonDemo;

    public static DoubleCheckLockSingletonDemo getInstance() throws InterruptedException {
        Thread.sleep(100);
        if(singletonDemo == null){
            synchronized (DoubleCheckLockSingletonDemo.class){
                if(singletonDemo == null){
                    // new的过程不是原子的，可能受到重排序影响
                    singletonDemo = new DoubleCheckLockSingletonDemo();
                }
            }
        }
        return singletonDemo;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.err.println(DoubleCheckLockSingletonDemo.getInstance());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
