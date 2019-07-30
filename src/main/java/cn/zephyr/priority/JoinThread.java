package cn.zephyr.priority;

/**
 * @ClassName: JoinThread
 * @Author: laizonghao
 * @Description: join()方法用例
 * @Date: 2019-07-30 16:04
 */
public class JoinThread {
    public static void main(String[] args) throws InterruptedException {
        Thread thread01 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        Thread.sleep(100);
                        System.err.println("子线程" + i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread01.start();
        thread01.join();
        for (int i = 0; i < 10; i++) {
            Thread.sleep(100);
            System.out.println("主线程" + i);
        }
    }
}
