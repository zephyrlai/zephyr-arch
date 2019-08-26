package cn.zephyr.annoation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.METHOD)
/**
 * @ClassName: MyTranscation
 * @Author: laizonghao
 * @Description: 自定义事务注解
 */
public @interface MyTranscation {

}
