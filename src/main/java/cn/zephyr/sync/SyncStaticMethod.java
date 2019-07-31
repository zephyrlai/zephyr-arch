package cn.zephyr.sync;

/**
 * @ClassName: SyncStaticMethod
 * @Author: laizonghao
 * @Description: 同步静态方法 todo
 * @Date: 2019-07-30 23:05
 */
public class SyncStaticMethod {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Thread02(), "窗口01");
        Thread thread2 = new Thread(new Thread02(), "窗口02");
        thread1.start();
        thread2.start();
    }
}

class Thread02 implements Runnable{

    private static Integer count = 100;

    @Override
    public void run() {
        while(count>0){
            if(count%2==0){
                synchronized (Thread02.class){
                    if(count>0){
                        System.err.println(Thread.currentThread().getName()+"--代码块--"+(100-count+1));
                        count--;
                    }
                }
            }else{
                sale();
            }
        }
    }

    private static synchronized void sale(){
        if(count>0){
            System.err.println(Thread.currentThread().getName()+"--方法--"+(100-count+1));
            count--;
        }
    }
}
