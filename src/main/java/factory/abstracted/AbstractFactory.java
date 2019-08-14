package factory.abstracted;

/**
 * @ClassName: AbstractFactory
 * @Author: laizonghao
 * @Description: 抽象工厂
 * @Date: 2019-08-12 11:38
 */
public interface AbstractFactory {
    Mouse produceMouse();

    Keyboard produceKeyboard();
}
