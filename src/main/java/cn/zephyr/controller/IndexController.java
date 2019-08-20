package cn.zephyr.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: IndexController
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-21 00:08
 */
@RestController
@RequestMapping("/")
public class IndexController {

    @RequestMapping("/")
    public void index(){
        // do nothing;
//        System.err.println(11);
    }
}
