package cn.zephyr.railway;

/**
 * @ClassName: ThreadNotSafe
 * @Author: laizonghao
 * @Description: 火车票为例的线程不安全示例
 * @Date: 2019-07-30 17:16
 */
public class ThreadNotSafe {
    public static void main(String[] args) {
        Thread01 thread1 = new Thread01();
        Thread thread01 = new Thread(thread1,"窗口1");
        Thread thread02 = new Thread(thread1,"窗口2");
        thread01.start();
        thread02.start();
    }
}
class Thread01 implements  Runnable{

    private Integer count=10;

    @Override
    public void run() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(count>0)
            sale();
    }

    private void sale(){
        // 有必要在此处进行判断
        if(count>0)
            System.err.println(Thread.currentThread().getName()+",当前出售——"+(10-count--+1) );
    }
}


