package factory.simple;

/**
 * @ClassName: Client
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-12 10:28
 */
public class Client {
    public static void main(String[] args) {
        new SimpleFactory().produceCar("auto").run();
        new SimpleFactory().produceCar("tesla").run();
    }
}
