package cn.zephyr;

/**
 * @ClassName: BMWCarModel
 * @Author: laizonghao
 * @Description: 具体产品
 * @Date: 2019-08-20 14:41
 */
public class BMWCarModel extends CarModel {
    @Override
    public void start() {
        System.err.println("BMW car model starting...");
    }

    @Override
    public void light() {
        System.err.println("BMW car lights on...");
    }

    @Override
    public void stop() {
        System.err.println("BMW car stopped...");
    }
}
