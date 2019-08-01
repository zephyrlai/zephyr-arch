package cn.zephyr.readwritedemo;

/**
 * @ClassName: WriteSafeDemo
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-01 09:57
 */
public class WriteSafeDemo {
    public static void main(String[] args) {
        DemoEntity demoEntity = new DemoEntity();
        Thread writeDemo = new Thread(new WriteThread02(demoEntity));
        Thread readDemo = new Thread(new ReadThread02(demoEntity));
        writeDemo.start();
        readDemo.start();
    }
}

class ReadThread02 implements Runnable{

    private DemoEntity demoEntity;

    public ReadThread02(DemoEntity demoEntity) {
        this.demoEntity = demoEntity;
    }

    @Override
    public void run() {
        while(true){
            System.err.println(demoEntity.getName()+"--"+demoEntity.getGender());
        }
    }
}

class WriteThread02 implements Runnable{

    private DemoEntity demoEntity;

    private Integer count = 0;

    public WriteThread02(DemoEntity demoEntity) {
        this.demoEntity = demoEntity;
    }

    @Override
    public void run() {
        while(true){
            synchronized (demoEntity){
                if(count==0){
                    demoEntity.setName("小明");
                    demoEntity.setGender("男");
                }else{
                    demoEntity.setName("小红");
                    demoEntity.setGender("女");
                }
                count= ++count % 2;
            }
        }
    }
}