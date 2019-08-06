package cn.zephyr.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: SynchronizedDemo
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-06 10:59
 */
public class SynchronizedDemo {

    public static void main(String[] args) {
        new Thread(new cn.zephyr.lock.SyncThread()).start();
    }
}

class SyncThread implements Runnable{

    @Override
    public void run() {
        method02();
    }

    /**
     * 持有this锁
     */
    public synchronized void method01(){
        System.out.println(Thread.currentThread().getName()+"--"+"这里是method01");

    }
    /**
     * 持有this锁，内部调用添加this锁的方法（如果不可重入则会死锁）
     */
    public synchronized void method02(){
        method01();
        System.err.println(Thread.currentThread().getName()+"--"+"这里是method02");
    }
}

