package cn.zephyr.threadlocal;

/**
 * @ClassName: ThreadlocalDemo
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-07-31 16:13
 */
public class ThreadlocalDemo {
    public static void main(String[] args) {
        Thread05 thread = new Thread05();
//        Thread06 thread = new Thread06();
        Thread thread1 = new Thread(thread);
        Thread thread2 = new Thread(thread);
        thread1.start();
        thread2.start();
    }
}

class Thread05 implements  Runnable{

    private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.err.println(Thread.currentThread().getName()+"----"+getCount());
        }
    }

    private synchronized Integer getCount(){
        Integer count = threadLocal.get();
        threadLocal.set(++count);
        return count;
    }
}
class Thread06 implements  Runnable{

    private Integer count = 0;
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.err.println(Thread.currentThread().getName()+"----"+getCount());
        }
    }

    private synchronized Integer getCount(){
        return ++count;
    }
}
