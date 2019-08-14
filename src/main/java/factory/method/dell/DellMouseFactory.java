package factory.method.dell;

import factory.method.Mouse;
import factory.method.MouseFactory;

/**
 * @ClassName: DellMouseFactory
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-12 11:29
 */
public class DellMouseFactory implements MouseFactory {
    @Override
    public Mouse produce() {
        return new DellMouse();
    }
}
