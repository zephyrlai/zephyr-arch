package cn.zephyr.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: MyParam
 * @Author: laizonghao
 * @Description:
 * @Date: 2019/9/4 11:55
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface MyParam {
    String value();
}
