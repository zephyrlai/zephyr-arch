package cn.zephyr.create;

/**
 * @ClassName: ThreadDemo02
 * @Author: laizonghao
 * @Description: 实现Runnable接口方式【推荐使用】
 * @Date: 2019-07-30 13:47
 */
public class ThreadDemo02 implements Runnable {
    @Override
    public void run() {
        System.err.println("==这里是子线程==");
        for (int i = 0; i < 10; i++) {
            System.err.println("子线程："+i);
        }
    }
}

class Thread02{
    public static void main(String[] args) {
        ThreadDemo02 threadDemo02 = new ThreadDemo02();
        new Thread(threadDemo02).start();
        System.out.println("==这里是主线程==");
        for (int i = 0; i < 10; i++) {
            System.out.println("主线程："+i);
        }
    }
}
