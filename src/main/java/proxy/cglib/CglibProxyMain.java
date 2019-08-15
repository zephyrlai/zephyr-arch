package proxy.cglib;

/**
 * @ClassName: CglibProxyMain
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-15 00:05
 */
public class CglibProxyMain {
    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        BusiDaoImpl realBusiDao = (BusiDaoImpl)cglibProxy.getInstance(new BusiDaoImpl());
        System.err.println(realBusiDao.getClass().getName());
        realBusiDao.updateByPrimaryKey("1");
    }
}
