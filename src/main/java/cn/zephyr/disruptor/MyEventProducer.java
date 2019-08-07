package cn.zephyr.disruptor;

import com.lmax.disruptor.RingBuffer;


/**
 * @ClassName: MyEventProducer
 * @Author: laizonghao
 * @Description: 生产者
 * @Date: 2019-08-07 11:18
 */
public class MyEventProducer {
    private final RingBuffer<MyEvent> ringBuffer;

    public MyEventProducer(RingBuffer<MyEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(String value){
        //  1. 拿到ringBuffer事件队列的下一个槽位置
        long next = ringBuffer.next();
        try {
            // 2. 根据位置获取事件
            MyEvent myEvent = ringBuffer.get(next);
            // 3. 获取事件队列传递的数据
            myEvent.setContent(value+"");
            Thread.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.err.println("这里是生产者，即将发送数据");
//            4. 发布事件
            ringBuffer.publish(next);
        }
    }
}
