package cn.zephyr.railway;

/**
 * @ClassName: ThreadDemo
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-07-30 17:27
 */
public
class Thread02 implements  Runnable{
    private static Integer count=100;
    private String threadName;

    public Thread02(String threadName) {
        this.threadName = threadName;
    }

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

    private synchronized void sale(){
        if(count>0)
            System.err.println(threadName+",当前出售——"+(100-count--+1) );
    }
}
