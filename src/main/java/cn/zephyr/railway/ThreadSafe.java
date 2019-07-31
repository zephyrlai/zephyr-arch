package cn.zephyr.railway;

/**
 * @ClassName: ThreadSafe
 * @Author: laizonghao
 * @Description: 火车票为例的线程安全示例
 * @Date: 2019-07-30 17:16
 */
public class ThreadSafe {
    public static void main(String[] args) {
        Thread02 thread2 = new Thread02();
        Thread thread01 = new Thread(thread2,"窗口1");
        Thread thread02 = new Thread(thread2,"窗口2");
        thread01.start();
        thread02.start();
    }
}
class Thread02 implements  Runnable{
    private Integer count=10;
    @Override
    public void run() {
        while(count>0)
            sale();
    }

    private synchronized void sale(){
        // 有必要在此处进行判断
        if(count>0){
            System.err.println(Thread.currentThread().getName()+",当前出售——"+(10-count+1) );
            count--;
        }
    }
}

