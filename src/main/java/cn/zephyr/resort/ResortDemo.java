package cn.zephyr.resort;

/**
 * @ClassName: ResortDemo
 * @Author: laizonghao
 * @Description: 重排序示例
 * @Date: 2019-07-31 17:25
 */
public class ResortDemo {

    private static Integer a = 0,b = 0;
    private static Integer x = 0,y = 0;

    public static void main(String[] args) throws InterruptedException {
        do{
            a=b=x=y=0;
            Thread thread01 = new Thread(new Runnable() {
                @Override
                public void run() {
                    a = 1;  // 1
                    x = b;  // 2
                }
            });
            Thread thread02 = new Thread(new Runnable() {
                @Override
                public void run() {
                    b = 1;  // 3
                    y = a;  // 4
                }
            });
            thread01.start();
            thread02.start();
            thread01.join();thread02.join();
            System.out.println("(" + x + "," + y + ")");
        }while(x!=0 || y!=0);
    }
}