package cn.zephyr.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: MyService
 * @Author: laizonghao
 * @Description:
 * @Date: 2019/8/28 11:51
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyService {
}
