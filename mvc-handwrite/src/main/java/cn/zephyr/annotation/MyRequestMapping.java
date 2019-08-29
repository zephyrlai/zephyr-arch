package cn.zephyr.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: MyRequestMapping
 * @Author: laizonghao
 * @Description:
 * @Date: 2019/8/28 17:27
 */
@Documented
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyRequestMapping {
    String value() default "";
}
