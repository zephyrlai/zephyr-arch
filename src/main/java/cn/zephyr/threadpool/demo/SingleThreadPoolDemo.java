package cn.zephyr.threadpool.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName: SingleThreadPoolDemo
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-04 17:06
 */
public class SingleThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int tempNum = i;
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.err.println(Thread.currentThread().getName()+"--"+tempNum);
                }
            });
        }
    }
}
