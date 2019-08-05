package cn.zephyr.threadpool.analyse;

import java.util.concurrent.*;

/**
 * @ClassName: ThreadPoolAnalyseDemo
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-05 10:22
 */
public class ThreadPoolAnalyseDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 2,
                60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(3));
        poolExecutor.execute(new Thread01("001"));
        poolExecutor.execute(new Thread01("002"));
        poolExecutor.execute(new Thread01("003"));
        poolExecutor.execute(new Thread01("004"));
        poolExecutor.execute(new Thread01("005"));
//        poolExecutor.execute(new Thread01("006"));
        poolExecutor.shutdown();
    }
}

class Thread01 implements  Runnable{
    private String threadName;

    public Thread01(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println(Thread.currentThread().getName()+"--"+threadName);
    }
}
