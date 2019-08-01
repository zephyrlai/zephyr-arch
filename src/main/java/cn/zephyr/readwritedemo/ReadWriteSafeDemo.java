package cn.zephyr.readwritedemo;

/**
 * @ClassName: ReadWriteSafeDemo
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-01 10:04
 */
public class ReadWriteSafeDemo {
    public static void main(String[] args) {
        DemoEntity demoEntity = new DemoEntity();
        Thread writeDemo = new Thread(new WriteThread03(demoEntity));
        Thread readDemo = new Thread(new ReadThread03(demoEntity));
        writeDemo.start();
        readDemo.start();
    }
}

class ReadThread03 implements Runnable{

    private DemoEntity demoEntity;

    public ReadThread03(DemoEntity demoEntity) {
        this.demoEntity = demoEntity;
    }

    @Override
    public void run() {
        while(true){
            synchronized (demoEntity) {
                System.err.println(demoEntity.getName()+"--"+demoEntity.getGender());
            }
        }
    }
}

class WriteThread03 implements Runnable{

    private DemoEntity demoEntity;

    private Integer count = 0;

    public WriteThread03(DemoEntity demoEntity) {
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
