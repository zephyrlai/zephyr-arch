package cn.zephyr.waitandnotify;

import cn.zephyr.readwritedemo.DemoEntity;

/**
 * @ClassName: WaitAndNotifyDemo
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-01 14:48
 */
public class WaitAndNotifyDemo {
    public static void main(String[] args) {
        DemoEntity demoEntity = new DemoEntity();
        Thread writeDemo = new Thread(new WriteThread04(demoEntity));
        Thread readDemo = new Thread(new ReadThread04(demoEntity));
        writeDemo.start();
        readDemo.start();
    }
}

class ReadThread04 implements Runnable{

    private DemoEntity demoEntity;

    public ReadThread04(DemoEntity demoEntity) {
        this.demoEntity = demoEntity;
    }

    @Override
    public void run() {
        while(true){
            synchronized (demoEntity) {
                if(!demoEntity.getFlag()){
                    try {
                        demoEntity.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.err.println(demoEntity.getName()+"--"+demoEntity.getGender());
                demoEntity.setFlag(false);
                demoEntity.notify();
            }
        }
    }
}

class WriteThread04 implements Runnable{

    private DemoEntity demoEntity;

    private Integer count = 0;

    public WriteThread04(DemoEntity demoEntity) {
        this.demoEntity = demoEntity;
    }

    @Override
    public void run() {
        while(true){
            synchronized (demoEntity){
                if(demoEntity.getFlag()){
                    try {
                        demoEntity.wait();
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
                demoEntity.notify();
            }
        }
    }
}