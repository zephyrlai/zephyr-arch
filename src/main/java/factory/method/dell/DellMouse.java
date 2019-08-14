package factory.method.dell;

import factory.method.Mouse;

/**
 * @ClassName: DellMouse
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-12 11:29
 */
public class DellMouse implements Mouse {
    @Override
    public void use() {
        System.err.println("this is dell mouse");
    }
}
