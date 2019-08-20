package cn.zephyr.memory;

/**
 * @ClassName: StackOverFlowDemo
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-20 23:49
 */
public class StackOverFlowDemo {
    // 递归深度计数器
    private static Integer counter=0;
    // -Xss1m的递归深度：11641；-Xss5m的递归深度：63267
    public static void main(String[] args) {
        try {
            count();
        } catch (Throwable t) {
//            t.printStackTrace();
        }finally {
            System.err.println("递归深度："+counter);
        }
    }

    private static void count(){
        counter++;
        count();
    }
}
