package cn.zephyr;

import java.util.List;

/**
 * @ClassName: TeslaCarModel
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-20 14:42
 */
public class TeslaCarModel extends CarModel {
    @Override
    public void start() {
        System.err.println("Tesla car model is starting...");
    }

    @Override
    public void light() {
        System.err.println("Tesla car model lights on...");
    }

    @Override
    public void stop() {
        System.err.println("Tesla car model stopped...");
    }
}
