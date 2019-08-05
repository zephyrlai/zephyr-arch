package cn.zephyr.threadpool.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName: CacheThreadPoolDemo
 * @Author: laizonghao
 * @Description: 可缓存线程池
 * @Date: 2019-08-04 16:42
 */
public class CacheThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int tempNum = i;
            // （创建并）执行线程任务
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.err.println(Thread.currentThread().getName()+"--"+tempNum);
                }
            });
        }

    }
}
