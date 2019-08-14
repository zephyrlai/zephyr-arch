package factory.abstracted.razer;

import factory.abstracted.AbstractFactory;
import factory.abstracted.Keyboard;
import factory.abstracted.Mouse;

/**
 * @ClassName: RazerFactory
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-12 11:40
 */
public class RazerFactory implements AbstractFactory {
    @Override
    public Mouse produceMouse() {
        return new RazerMouse();
    }

    @Override
    public Keyboard produceKeyboard() {
        return new RazerKeyboard();
    }
}
