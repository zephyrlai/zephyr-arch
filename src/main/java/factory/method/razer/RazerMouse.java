package factory.method.razer;

import factory.method.Mouse;

/**
 * @ClassName: RazerMouse
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-12 11:27
 */
public class RazerMouse implements Mouse {
    @Override
    public void use() {
        System.err.println("this is razer mouse.");
    }
}
