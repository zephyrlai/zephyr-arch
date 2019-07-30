package cn.zephyr.create;

/**
 * @ClassName: ThreadDemo01
 * @Author: laizonghao
 * @Description: 方式一：继承Thread类
 * @Date: 2019-07-30 13:43
 */
public class ThreadDemo01 extends Thread{
    @Override
    public void run() {
        System.err.println("==这里是子线程==");
        for (int i = 0; i < 30; i++) {
            System.err.println("子线程："+i);
        }
    }
}

class Thread01{
    public static void main(String[] args) {
        ThreadDemo01 threadDemo01 = new ThreadDemo01();
        System.err.println("==这里是主线程==");
        threadDemo01.start();
        for (int i = 0; i < 10; i++) {
            System.out.println("主线程："+i);
        }
    }
}
