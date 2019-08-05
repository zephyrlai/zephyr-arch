package cn.zephyr.threadpool.demo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ScheduledThreadPoolDemo
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-04 17:00
 */
public class ScheduledThreadPoolDemo {
    public static void main(String[] args) {
        // 若干时间之后开始执行线程
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int tempNum = i;
            scheduledThreadPool.schedule(new Runnable() {
                @Override
                public void run() {
                    System.err.println(Thread.currentThread().getName()+"--"+tempNum);
                }
            },5, TimeUnit.SECONDS);
        }
    }
}
