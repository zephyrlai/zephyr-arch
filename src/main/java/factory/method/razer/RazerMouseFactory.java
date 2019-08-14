package factory.method.razer;

import factory.method.Mouse;
import factory.method.MouseFactory;

/**
 * @ClassName: RazerMouseFactory
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-12 11:27
 */
public class RazerMouseFactory  implements MouseFactory {

    @Override
    public Mouse produce() {
        return new RazerMouse();
    }
}
