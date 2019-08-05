package cn.zephyr.handwrite;

/**
 * @ClassName: MockRequest
 * @Author: laizonghao
 * @Description: 模拟业务请求
 * @Date: 2019-08-05 16:34
 */
public class MockData implements MyData {

    private String result;

    public MockData(String result) {
        try {
            System.err.println("正在请求数据。。。");
            Thread.sleep(3000);
            System.err.println("请求完成。。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.result = result;
    }

    @Override
    public String get() {
        return result;
    }
}
