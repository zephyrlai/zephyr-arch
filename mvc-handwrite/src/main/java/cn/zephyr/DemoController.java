package cn.zephyr;

import cn.zephyr.annotation.MyController;
import cn.zephyr.annotation.MyRequestMapping;

/**
 * @ClassName: DemoController
 * @Author: laizonghao
 * @Description:
 * @Date: 2019/8/28 18:03
 */
@MyController
@MyRequestMapping("/1")
public class DemoController {

    @MyRequestMapping("/2")
    public String index(){
        return "index";
    }
}
