package cn.zephyr.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: MyAutowire
 * @Author: laizonghao
 * @Description:
 * @Date: 2019/8/28 11:55
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAutowire {
}
