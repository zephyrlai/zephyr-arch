package factory.abstracted;

import factory.abstracted.dell.DellFactory;
import factory.abstracted.razer.RazerFactory;

/**
 * @ClassName: AbstractFactoryClient
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-12 11:42
 */
public class AbstractFactoryClient {
    public static void main(String[] args) {
        // Razer
        AbstractFactory factory = new RazerFactory();
        factory.produceMouse().use();
        factory.produceKeyboard().use();
        // Dell
        factory = new DellFactory();
        factory.produceMouse().use();
        factory.produceKeyboard().use();
    }
}
