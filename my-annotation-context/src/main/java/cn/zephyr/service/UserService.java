package cn.zephyr.service;

import cn.zephyr.annotation.MyAutowire;
import cn.zephyr.annotation.MyService;

/**
 * @ClassName: UserService
 * @Author: laizonghao
 * @Description:
 * @Date: 2019/8/28 11:50
 */
@MyService
public class UserService {

    @MyAutowire
    private OrderService orderService;

    public void queryUser(){
        System.err.println("==用户查询==");
        orderService.queryList();
    }
}
