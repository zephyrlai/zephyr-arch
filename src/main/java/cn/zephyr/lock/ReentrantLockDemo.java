package cn.zephyr.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: ReentrantLockDemo
 * @Author: laizonghao
 * @Description: 可重入锁demo
 * @Date: 2019-08-06 09:36
 */
public class ReentrantLockDemo {

    public static void main(String[] args) {
        new Thread(new Thread01()).start();
    }
}

class Thread01 implements Runnable{

    ReentrantLock reentrantLock = new ReentrantLock();

    @Override
    public void run() {
        method02();
    }

    public void method01(){
        try {
            reentrantLock.lock();
            System.out.println(Thread.currentThread().getName()+"--这里是method01");
        } finally {
            reentrantLock.unlock();
        }
    }

    public void method02(){
        try {
            reentrantLock.lock();
            method01();
            System.err.println(Thread.currentThread().getName()+"--这里是method02");
        } finally {
            reentrantLock.unlock();
        }
    }
}

