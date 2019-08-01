package cn.zephyr.readwritedemo;

/**
 * @ClassName: OriginDemo
 * @Author: laizonghao
 * @Description: 读写不安全示例
 * @Date: 2019-08-01 09:35
 */
public class OriginDemo {
    public static void main(String[] args) {
        DemoEntity demoEntity = new DemoEntity();
        Thread writeDemo = new Thread(new WriteThread01(demoEntity));
        Thread readDemo = new Thread(new ReadThread01(demoEntity));
        writeDemo.start();
        readDemo.start();
    }
}

class ReadThread01 implements Runnable{

    private DemoEntity demoEntity;

    public ReadThread01(DemoEntity demoEntity) {
        this.demoEntity = demoEntity;
    }

    @Override
    public void run() {
        while(true){
            System.err.println(demoEntity.getName()+"--"+demoEntity.getGender());
        }
    }
}

class WriteThread01 implements Runnable{

    private DemoEntity demoEntity;

    private Integer count = 0;

    public WriteThread01(DemoEntity demoEntity) {
        this.demoEntity = demoEntity;
    }

    @Override
    public void run() {
        while(true){
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
