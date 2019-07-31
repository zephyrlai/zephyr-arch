package cn.zephyr.deadlock;

/**
 * @ClassName: DeadLockThread
 * @Author: laizonghao
 * @Description: 死锁示例
 * @Date: 2019-07-31 14:18
 */
public class DeadLockThread {
    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        Thread thread01 = new Thread(thread1);
        Thread thread02 = new Thread(thread1);
        thread01.start();
        thread02.start();
    }
}

class Thread1 implements Runnable{
    private Integer count=100;

    private final Object obj= new Object();

    @Override
    public void run() {
        while(count>0){
            if(count%2==0){
                // 重入锁
                // 先持有obj锁，后持有this锁
                synchronized (obj){
                    sale();
                }
            }else{
                // 先持有this锁，后持有obj锁
                sale();
            }
        }
    }

    private synchronized void sale(){
        // 睡眠堆积，助于演示死锁效果
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (obj){
            System.err.println(Thread.currentThread().getName()+"--方法--"+(100-count+1));
            count--;
        }
    }
}
