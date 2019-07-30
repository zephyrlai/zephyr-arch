package cn.zephyr.daemon;

/**
 * @ClassName: DeamonThread
 * @Author: laizonghao
 * @Description: 守护线程测试用例
 * @Date: 2019-07-30 15:14
 */
public class DaemonThread {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
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
        thread.setDaemon(true);
        thread.start();
        System.out.println("主线程开始执行==>");
        for (int i = 0; i < 10; i++) {
            Thread.sleep(20);
            System.out.println("主线程"+i);
        }
        System.out.println("主线程结束执行<==");
    }
}
