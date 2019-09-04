package cn.zephyr.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: MyInsert
 * @Author: laizonghao
 * @Description:
 * @Date: 2019/9/4 11:54
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyInsert {
    String value();
}
