package cn.zephyr.priority;

/**
 * @ClassName: PrioriryThread
 * @Author: laizonghao
 * @Description: 线程优先级示意线程(优先级效果不明显)
 * @Date: 2019-07-30 15:38
 */
public class PrioriryThread {
    public static void main(String[] args) {
        Thread thread01 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("高优先级"+i);
                }
            }
        });
        Thread thread02 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.err.println("低优先级："+i);
                }
            }
        });
        thread01.setPriority(Thread.MAX_PRIORITY);
        thread02.setPriority(Thread.MIN_PRIORITY);
        thread02.start();
        thread01.start();
    }
}
