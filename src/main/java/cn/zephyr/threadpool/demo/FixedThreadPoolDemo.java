package cn.zephyr.threadpool.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName: FixedThreadPoolDemo
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-04 16:51
 */
public class FixedThreadPoolDemo {
    public static void main(String[] args) {
        // 固定创建的线程总数
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 10; i++) {
            final int tempNum = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.err.println(Thread.currentThread().getName()+"--"+tempNum);
                }
            });
        }

    }
}
