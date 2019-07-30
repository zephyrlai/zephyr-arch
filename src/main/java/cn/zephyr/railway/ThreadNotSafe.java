package cn.zephyr.railway;

/**
 * @ClassName: NotSate
 * @Author: laizonghao
 * @Description: 火车票为例的线程不安全示例
 * @Date: 2019-07-30 17:16
 */
public class ThreadNotSafe {
    public static void main(String[] args) {
        Thread thread01 = new Thread(new Thread01("窗口1"));
        Thread thread02 = new Thread(new Thread01("窗口2"));
        thread01.start();
        thread02.start();
    }
}


