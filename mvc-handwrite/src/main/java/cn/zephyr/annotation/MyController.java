package cn.zephyr.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: MyController
 * @Author: laizonghao
 * @Description:
 * @Date: 2019/8/28 17:27
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyController {
}
