package factory.abstracted.dell;

import factory.abstracted.Mouse;

/**
 * @ClassName: DellMouse
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-12 11:44
 */
public class DellMouse implements Mouse {
    @Override
    public void use() {
        System.out.println("this is dell mouse.");
    }
}
