package cn.zephyr.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * @ClassName: MyEventConsumer
 * @Author: laizonghao
 * @Description: 定义事件处理器（消费者）
 * @Date: 2019-08-07 11:16
 */
public class MyEventConsumer implements EventHandler<MyEvent> {
    @Override
    public void onEvent(MyEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.err.println("这里是消费者01，事件内容是："+event.getContent());
    }
}
