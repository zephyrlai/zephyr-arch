package cn.zephyr.sync;

/**
 * @ClassName: SyncInstanceMethod
 * @Author: laizonghao
 * @Description: 同步实例方法：持有对象锁
 * @Date: 2019-07-30 23:05
 */
public class SyncInstanceMethod {
    public static void main(String[] args) {
        Thread01 thread01 = new Thread01();
        Thread thread1 = new Thread(thread01, "窗口01");
        Thread thread2 = new Thread(thread01, "窗口02");
        thread1.start();
        thread2.start();
    }
}

class Thread01 implements Runnable{

    private Integer count = 100;

    @Override
    public void run() {
        while(count>0){
            if(count%2==0){
                synchronized (this){
                    if(count>0){
                        System.err.println(Thread.currentThread().getName()+"--"+(100-count+1));
                        count--;
                    }
                }
            }else{
                sale();
            }
        }
    }
    private synchronized void sale(){
        if(count>0){
            System.err.println(Thread.currentThread().getName()+"--"+(100-count+1));
            count--;
        }
    }
}