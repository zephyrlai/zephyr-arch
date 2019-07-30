package cn.zephyr.priority;

/**
 * @ClassName: YieldThread
 * @Author: laizonghao
 * @Description: yield()方法用例
 * @Date: 2019-07-30 16:09
 */
public class YieldThread {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread.yield();
                for (int i = 0; i < 10; i++) {
                    System.err.println("子线程" + i);
                }
            }
        });
        thread.start();
        for (int i = 0; i < 10; i++) {
            System.out.println("主线程"+i);
        }
    }
}
