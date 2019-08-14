package factory.simple;

/**
 * @ClassName: AutoCar
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-12 10:26
 */
public class AutoCar implements Car {
    @Override
    public void run() {
        System.err.println("this is auto car.");
    }
}
