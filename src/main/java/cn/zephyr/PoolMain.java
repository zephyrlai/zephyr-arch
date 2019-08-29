package cn.zephyr;

import java.sql.Connection;

/**
 * @ClassName: PoolMain
 * @Author: laizonghao
 * @Description:
 * @Date: 2019/8/29 16:47
 */
public class PoolMain {
    public static void main(String[] args) {

//        for (int i = 0; i < 10; i++) {
        for (int i = 0; i < 50; i++) {
            new Thread(new MyThread()).start();
        }

    }
}

class MyThread implements Runnable{

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                Connection connection = new MyDbConnectionPool().getConnection();
                System.err.println(Thread.currentThread().getName()+"--"+connection);
                MyDbConnectionPool.releaseConnection(connection);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
