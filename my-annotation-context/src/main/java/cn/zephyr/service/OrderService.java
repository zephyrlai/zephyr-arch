package cn.zephyr.service;

import cn.zephyr.annotation.MyService;

/**
 * @ClassName: OrderService
 * @Author: laizonghao
 * @Description:
 * @Date: 2019/8/28 11:54
 */
@MyService
public class OrderService {
    public void queryList(){
        System.err.println("==订单查询==");
    }
}
