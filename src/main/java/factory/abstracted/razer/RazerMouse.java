package factory.abstracted.razer;

import factory.abstracted.Mouse;

/**
 * @ClassName: RazerMouse
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-12 11:40
 */
public class RazerMouse implements Mouse {
    @Override
    public void use() {
        System.err.println("this is razer mouse");
    }
}
