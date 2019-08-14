package factory.abstracted.dell;

import factory.abstracted.AbstractFactory;
import factory.abstracted.Keyboard;
import factory.abstracted.Mouse;

/**
 * @ClassName: DellFactory
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-12 11:44
 */
public class DellFactory implements AbstractFactory {
    @Override
    public Mouse produceMouse() {
        return new DellMouse();
    }

    @Override
    public Keyboard produceKeyboard() {
        return new DellKeyboard();
    }
}
