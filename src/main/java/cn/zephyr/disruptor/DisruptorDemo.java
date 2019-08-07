package cn.zephyr.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName: DisruptorDemo
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-07 11:12
 */
public class DisruptorDemo {

    public static void main(String[] args) {
//        1. 创建一个可缓存线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();
//        2. 创建事件工厂
        MyEventFactory myEventFactory = new MyEventFactory();
//        3. 定义ringBuffer大小
        int ringBufferSize=1024 * 1024;
//        4. 创建Disruptor
        Disruptor<MyEvent> disruptor = new Disruptor<MyEvent>(myEventFactory, ringBufferSize, threadPool, ProducerType.SINGLE, new YieldingWaitStrategy());
//        5. 注册消费者
        disruptor.handleEventsWith(new MyEventConsumer());
//        disruptor.handleEventsWith(new MyEventConsumer2());
//        6. 启动
        disruptor.start();
//        7. 创建RingBuffer容器
        RingBuffer<MyEvent> ringBuffer = disruptor.getRingBuffer();
//        8. 创建生产者
        MyEventProducer myEventProducer = new MyEventProducer(ringBuffer);
//        9. 指定缓冲区大小：8
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        for (int i = 0; i < 20; i++) {
            myEventProducer.onData(i+"");
        }
//        10. 关闭
        disruptor.shutdown();
        threadPool.shutdown();
    }
}
