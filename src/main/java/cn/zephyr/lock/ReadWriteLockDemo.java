package cn.zephyr.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName: ReadWriteLock
 * @Author: laizonghao
 * @Description: 读写锁demo
 * @Date: 2019-08-06 09:36
 */
public class ReadWriteLockDemo {
    
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    MyCache.write("key"+i,"value"+i);
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    MyCache.read("key"+i);
                }
            }
        }).start();
    }
}

class MyCache{
    private static Map<String,String> globalCache = new HashMap<String, String>();

    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
    
    public static String read(String key){
        String s;
        try {
            readLock.lock();
            System.out.println("读操作开始");
            s = globalCache.get(key);
            System.out.println("读操作结束,读取值："+s);
        } finally {
            readLock.unlock();
        }
        return s;
    }
    
    public static void write(String key,String value){
        try {
            writeLock.lock();
            System.err.println("写操作开始");
            globalCache.put(key,value);
            System.err.println("写操作结束，写入键值对："+key+"--"+value);
        } finally {
            writeLock.unlock();
        }
    }
}
