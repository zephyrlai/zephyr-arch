package cn.zephyr.handwrite;

/**
 * @ClassName: FutureMockRequest
 * @Author: laizonghao
 * @Description: 手写Future
 * @Date: 2019-08-05 16:35
 */
public class FutureData implements MyData {

    private Boolean flag=false;

    private MockData mockData;


    @Override
    public synchronized String get() {
        while(!flag){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return mockData.get();
    }

    public synchronized void setResult(MockData result){
        if(flag){
            return;
        }else{
            this.mockData = result;
            flag = true;
            notify();
        }
    }
}
