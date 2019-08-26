package cn.zephyr.aop.service.impl;

import cn.zephyr.aop.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * @ClassName: OrderServiceImpl
 * @Author: laizonghao
 * @Description:
 * @Date: 2019/8/25 17:18
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public void queryList() {
        System.err.println("订单列表查询");
    }
}
