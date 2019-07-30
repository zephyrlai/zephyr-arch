package cn.zephyr.create;

/**
 * @ClassName: ThreadDemo03
 * @Author: laizonghao
 * @Description: 匿名内部类方式
 * @Date: 2019-07-30 13:50
 */
public class ThreadDemo03 {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.err.println("子线程" + i);
                }
            }
        }).start();
        for (int i = 0; i < 10; i++) {
            System.out.println("主线程" + i);
        }
    }

}
