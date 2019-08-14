package factory.abstracted.razer;

import factory.abstracted.Keyboard;

/**
 * @ClassName: RazerKeyboard
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-12 11:41
 */
public class RazerKeyboard implements Keyboard {
    @Override
    public void use() {
        System.err.println("this is raser keyboard.");
    }
}
