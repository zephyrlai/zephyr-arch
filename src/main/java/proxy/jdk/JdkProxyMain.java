package proxy.jdk;

import java.lang.reflect.Proxy;

/**
 * @ClassName: JdkProxyMain
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-14 23:56
 */
public class JdkProxyMain {

    public static void main(String[] args) {
        JdkProxy jdkProxy = new JdkProxy(new BusiDaoImpl());
        BusiDao realBusiDao = (BusiDao)Proxy.newProxyInstance(BusiDaoImpl.class.getClassLoader(),BusiDaoImpl.class.getInterfaces(),jdkProxy);
        realBusiDao.updateByPrimaryKey("1");
    }
}
