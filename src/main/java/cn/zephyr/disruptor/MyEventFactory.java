package cn.zephyr.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @ClassName: MyEventFactory
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-07 11:14
 */
public class MyEventFactory implements EventFactory<MyEvent> {

    @Override
    public MyEvent newInstance() {
        return new MyEvent();
    }
}
