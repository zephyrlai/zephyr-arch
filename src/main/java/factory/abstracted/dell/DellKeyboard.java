package factory.abstracted.dell;

import factory.abstracted.Keyboard;

/**
 * @ClassName: DellKeyboard
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-12 11:44
 */
public class DellKeyboard implements Keyboard {
    @Override
    public void use() {
        System.out.println("this is dell keyboard.");
    }
}
