package cn.zephyr.vola;

/**
 * @ClassName: VolatileDemo
 * @Author: laizonghao
 * @Description: volatile关键字示例
 * @Date: 2019-07-31 16:42
 */
public class VolatileDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread06 thread06 = new Thread06();
        Thread thread = new Thread(thread06);
        thread.start();
        Thread.sleep(2000);
        thread06.setFlag(true);
        Thread.sleep(2000);
    }
}

class Thread06 implements Runnable{

    private volatile Boolean flag = false;

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        System.err.println("线程开始执行 ==》");
        while(!flag){}
        System.err.println("线程结束执行《==");
    }
}
