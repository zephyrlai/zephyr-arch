package cn.zephyr.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;

import java.lang.reflect.Method;

/**
 * @ClassName: ModifyClassDemo
 * @Author: laizonghao
 * @Description: 使用javassist修改类(添加方法)
 * @Date: 2019-08-21 22:48
 */
public class ModifyClassDemo {
    public static void main(String[] args) throws Exception {
        ClassPool classPool = ClassPool.getDefault();
        // 1. 定位目标类
        CtClass ctClass = classPool.get("cn.zephyr.javassist.User");
        // 2. 创建并添加方法
        CtMethod ctMethod = new CtMethod(CtClass.intType, "printSum", new CtClass[]{CtClass.intType, CtClass.intType}, ctClass);
        ctMethod.setBody("return $1+$2;");
        ctMethod.setModifiers(Modifier.PUBLIC);
        ctClass.addMethod(ctMethod);
        // 3. 输出class文件
        ctClass.writeFile("target/classes");

        // 使用新方法
        Class<?> clazz = ctClass.toClass();
        Object user = clazz.newInstance();
        Method printSum = clazz.getDeclaredMethod("printSum", int.class, int.class);
        Object result = printSum.invoke(user, 11, 22);
        System.err.println(result);
    }
}
