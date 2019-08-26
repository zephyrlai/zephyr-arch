package cn.zephyr.advice;

import cn.zephyr.annoation.MyTranscation;
import cn.zephyr.utils.TransactionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;

import java.lang.reflect.Method;

/**
 * @ClassName: TranscationAdvice
 * @Author: laizonghao
 * @Description:
 * @Date: 2019/8/26 16:50
 */
@Aspect
@Component
public class TranscationAdvice {
    @Autowired
    private TransactionUtils transactionUtils;

    @Pointcut("execution(* cn.zephyr.service.*Service.*(..))")
    private void trancationPointCut(){};

//    @Before("trancationPointCut()")
    public void beforeHandler(){
        System.err.println("前置增强===");
    }

    @Around("trancationPointCut()")
    public void aroundAdviceHandler(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MyTranscation annotation = getMyTranscation(proceedingJoinPoint);
        TransactionStatus transactionStatus = null;
        if(null != annotation) {
            System.err.println("==事务开始==");
            transactionStatus = transactionUtils.begin();
        }
        proceedingJoinPoint.proceed();
        if(null!= transactionStatus){
            System.err.println("==事务提交==");
            transactionUtils.commit(transactionStatus);
        }
    }

    @AfterThrowing("trancationPointCut()")
    public void exceptionHandler() {
        System.err.println("===全局异常处理===");
        transactionUtils.rollback();
    }

    private MyTranscation getMyTranscation(ProceedingJoinPoint proceedingJoinPoint) throws NoSuchMethodException {
        // 获取方法签名
        Signature signature = proceedingJoinPoint.getSignature();
        // 获取方法名
        String methodName = signature.getName();
        // 获取方法入参
        Class[] parameterTypes = ((MethodSignature)signature).getParameterTypes();
        // 获取目标类
        Class<?> clazz = proceedingJoinPoint.getTarget().getClass();
        Method declaredMethod = clazz.getDeclaredMethod(methodName, parameterTypes);
        // 获取方法上的指定注解
        return declaredMethod.getAnnotation(MyTranscation.class);
    }
}
