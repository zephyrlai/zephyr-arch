package cn.zephyr.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @ClassName: AopDemo
 * @Author: laizonghao
 * @Description:
 * @Date: 2019/8/24 17:56
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class,args);
    }
}
