package cn.zephyr.handwrite;

/**
 * @ClassName: Main
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-05 16:53
 */
public class Main {
    public static void main(String[] args) {
        // 新建线程并请求，目前获取到的结果对象是一个没有内容的对象（线程执行完成后，会将数据写入到同一个对象）
        FutureData futureData = new FutureClient().start("入参入参");
        System.err.println("主线程已发送请求");
        System.err.println("主线程执行其他业务逻辑");
        // 阻塞式获取
        String data = futureData.get();
        System.err.println("获取子线程数据："+data);
    }
}
