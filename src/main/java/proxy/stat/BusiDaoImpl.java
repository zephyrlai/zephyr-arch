package proxy.stat;

/**
 * @ClassName: BusiDaoImpl
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-14 23:41
 */
public class BusiDaoImpl implements BusiDao {
    @Override
    public void updateByPrimaryKey(String id){
        System.err.println("==执行sql更新==");
    }
}
