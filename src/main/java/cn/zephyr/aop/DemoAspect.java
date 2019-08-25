package cn.zephyr.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @ClassName: DemoAspect
 * @Author: laizonghao
 * @Description:
 * @Date: 2019/8/24 17:34
 */
@Component
@Aspect
public class DemoAspect {

    @Pointcut("within(cn.zephyr.aop.service..*)")
    private void myPointCut(){};

    @Before("myPointCut()")
    public void BeforeHander(){
        System.err.println("==前置通知==");
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After("execution( * cn.zephyr.aop.service..*(..))")
    public void AfterHandler(){
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("==后置通知==");
    }

    @AfterReturning("execution( * cn.zephyr.aop.service..*(..))")
    public void AfterReturningHandler(){
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("==执行后通知==");
    }

    @AfterThrowing("execution( * cn.zephyr.aop.service..*(..))")
    public void AfterThrowingHandler(){
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("==异常捕获==");
    }

//    @Around("this(cn.zephyr.aop.service.UserService)")
    public Object aroundThisHander(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.err.println("==环绕通知this--前通知==");
        Object result = null;
        try {
            Thread.sleep(10);
            result = proceedingJoinPoint.proceed();
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("==环绕通知this--后通知==");
        return result;
    }



}
