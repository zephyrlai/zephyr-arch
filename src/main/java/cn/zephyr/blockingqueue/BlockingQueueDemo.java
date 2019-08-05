package cn.zephyr.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: BlockingQueueDemo
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-03 17:33
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new LinkedBlockingDeque<String>();
        Thread producerThread = new Thread(new ProducerThread(blockingQueue));
        Thread consumerThread = new Thread(new ConsumerThread(blockingQueue));
        producerThread.start();
        consumerThread.start();
        Thread.sleep(10* 1000);
        producerThread.stop();
    }

}


/**
 * 生产者线程
 */
class ProducerThread implements Runnable{

    private BlockingQueue<String> blockingQueue;
    // 线程安全Integer
    private AtomicInteger atomicInteger = new AtomicInteger();

    public void setBlockingQueue(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public ProducerThread(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while(true){
            try {
                String data = atomicInteger.incrementAndGet() + "";
                boolean offer = blockingQueue.offer(data,2, TimeUnit.SECONDS);
                if(offer){
                    System.out.println("数据存储成功，存储的数据是："+data);
                }else{
                    System.err.println("数据存储失败，存储的数据是："+data);
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class ConsumerThread implements Runnable{
    private BlockingQueue<String> blockingQueue;

    public void setBlockingQueue(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public ConsumerThread(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while(true){
            try {
                String data = blockingQueue.poll(2, TimeUnit.SECONDS);
                if(null != data){
                    System.out.println("数据消费成功，对应的数据是："+data);
                }else{
                    System.err.println("请求超时，数据消费失败");
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}