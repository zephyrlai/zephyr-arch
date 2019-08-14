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
        BusiDao realBusiDao = (BusiDao)cglibProxy.getInstance(new BusiDaoImpl());
        realBusiDao.updateByPrimaryKey("1");
    }
}
