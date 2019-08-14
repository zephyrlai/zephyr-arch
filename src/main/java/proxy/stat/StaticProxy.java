package proxy.stat;

/**
 * @ClassName: StaticProxy
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-14 23:39
 */
public class StaticProxy implements BusiDao {
    private BusiDao busiDao;

    public StaticProxy(BusiDao busiDao) {
        this.busiDao = busiDao;
    }

    @Override
    public void updateByPrimaryKey(String id) {
        System.err.println("==>开启数据库事务");
        busiDao.updateByPrimaryKey(id);
        System.err.println("<==关闭数据库事务");
    }
}
