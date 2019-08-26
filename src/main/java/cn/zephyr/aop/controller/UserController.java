package cn.zephyr.aop.controller;

import cn.zephyr.aop.service.OrderService;
import cn.zephyr.aop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: UserController
 * @Author: laizonghao
 * @Description:
 * @Date: 2019/8/24 18:04
 */
@RequestMapping("/")
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/")
    public String index() throws InterruptedException {
        userService.insert(new Object());
        System.err.println("service 执行完毕");
        return "succ";
    }

    @RequestMapping("2")
    public String index02(){
        userService.insert(new Object());
        OrderService orderService1 = (OrderService)userService;
        orderService1.queryList();
        return "index02";
    }
}
