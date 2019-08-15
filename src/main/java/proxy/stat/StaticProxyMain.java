package proxy.stat;

/**
 * @ClassName: StaticProxyMain
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-14 23:47
 */
public class StaticProxyMain {
    public static void main(String[] args) {
        BusiDao realBusiDao = new StaticProxy(new BusiDaoImpl());
        System.err.println(realBusiDao);
        realBusiDao.updateByPrimaryKey("1");
    }
}
