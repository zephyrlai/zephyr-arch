package cn.zephyr.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

/**
 * @ClassName: TransactionUtils
 * @Author: laizonghao
 * @Description: 事务控制工具类
 * @Date: 2019/8/26 17:10
 */
@Component
@Scope("prototype")
public class TransactionUtils {
    @Autowired
    private JpaTransactionManager jpaTransactionManager;

    private TransactionStatus transactionStatus;

    // 开启事务
    public TransactionStatus begin() {
        transactionStatus = jpaTransactionManager.getTransaction(new DefaultTransactionAttribute());
        return transactionStatus;
    }

    // 提交事务
    public void commit(TransactionStatus transactionStatus) {
        jpaTransactionManager.commit(transactionStatus);
    }

    // 回滚事务
    public void rollback() {
        if(null != transactionStatus)
            jpaTransactionManager.rollback(transactionStatus);
    }
}
