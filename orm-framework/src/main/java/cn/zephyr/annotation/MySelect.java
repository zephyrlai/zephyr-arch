package cn.zephyr.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: MySelect
 * @Author: laizonghao
 * @Description:
 * @Date: 2019/9/4 14:02
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MySelect {
    String value();
}
