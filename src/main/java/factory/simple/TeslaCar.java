package factory.simple;

/**
 * @ClassName: TeslaCar
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-12 10:26
 */
public class TeslaCar implements Car {
    @Override
    public void run() {
        System.err.println("this is tesla car.");
    }
}
