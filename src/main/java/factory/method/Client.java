package factory.method;

import factory.method.dell.DellMouseFactory;
import factory.method.razer.RazerMouseFactory;

/**
 * @ClassName: Client
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-12 11:28
 */
public class Client {
    public static void main(String[] args) {
        new RazerMouseFactory().produce().use();
        new DellMouseFactory().produce().use();
    }
}
