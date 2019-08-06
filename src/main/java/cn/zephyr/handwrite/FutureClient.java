package cn.zephyr.handwrite;

/**
 * @ClassName: FutureClient
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-05 16:47
 */
public class FutureClient {

    public FutureData start(final String params){
        final FutureData futureMockRequest = new FutureData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 模拟业务请求（构造方法中调用了Thread.sleep()方法）
                MockData mockRequest = new MockData(params + "--哈哈");
                futureMockRequest.setResult(mockRequest);
            }
        }).start();
        return futureMockRequest;
    }
}
