package cn.zephyr.lock;

import java.util.concurrent.locks.Condition;

/**
 * @ClassName: LockDemo
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-01 15:14
 */
public class LockDemo{
    public static void main(String[] args) {
        // 同一个实体
        LockDemoEntity demoEntity = new LockDemoEntity();
        // 同一个condition对象
        Condition condition = demoEntity.getLock().newCondition();
        Thread writeDemo = new Thread(new WriteThread05(demoEntity,condition));
        Thread readDemo = new Thread(new ReadThread05(demoEntity,condition));
        writeDemo.start();
        readDemo.start();
    }
}

class ReadThread05 implements Runnable{

    private LockDemoEntity demoEntity;

    private Condition condition;

    public ReadThread05(LockDemoEntity demoEntity, Condition condition) {
        this.demoEntity = demoEntity;
        this.condition = condition;
    }

    @Override
    public void run() {
        while(true){
            try {
                demoEntity.getLock().lock();
                if(!demoEntity.getFlag()){
                    try {
//                        demoEntity.wait();
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.err.println(demoEntity.getName()+"--"+demoEntity.getGender());
                demoEntity.setFlag(false);
                // demoEntity.notify();
                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                demoEntity.getLock().unlock();
            }
        }
    }
}

class WriteThread05 implements Runnable{

    private LockDemoEntity demoEntity;

    private Condition condition;

    private Integer count = 0;

    public WriteThread05(LockDemoEntity demoEntity, Condition condition) {
        this.demoEntity = demoEntity;
        this.condition = condition;
    }

    @Override
    public void run() {
        while(true){
            try {
                demoEntity.getLock().lock();
                if(demoEntity.getFlag()){
                    try {
    //                        demoEntity.wait();
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(count==0){
                    demoEntity.setName("小明");
                    demoEntity.setGender("男");
                }else{
                    demoEntity.setName("小红");
                    demoEntity.setGender("女");
                }
                count= ++count % 2;
                demoEntity.setFlag(true);
//                demoEntity.notify();
                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                demoEntity.getLock().unlock();
            }
        }
    }
}
