package cn.zephyr;

import java.lang.annotation.*;

/**
 * @ClassName: MyAnnotation
 * @Author: laizonghao
 * @Description:
 * @Date: 2019/8/26 20:09
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface MyAnnotation {
    int value() default 0;
    String name() default "";
}
