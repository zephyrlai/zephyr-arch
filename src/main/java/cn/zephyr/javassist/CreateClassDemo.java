package cn.zephyr.javassist;

import javassist.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @ClassName: CreateClassDemo
 * @Author: laizonghao
 * @Description: 使用javassist创建类
 * @Date: 2019-08-21 22:47
 */
public class CreateClassDemo {

    public static void main(String[] args) throws Exception {
        ClassPool classPool = ClassPool.getDefault();
        // 创建Class文件
        CtClass ctClass = classPool.makeClass("cn.zephyr.javassist.MyUser");
        // 创建并添加属性
        CtField nameField = CtField.make("private String name;", ctClass);
        CtField ageField = CtField.make("private int age;", ctClass);
        ctClass.addField(nameField);
        ctClass.addField(ageField);
        // 创建并添加方法
        CtMethod getNameMethod = CtMethod.make("public String getName() {return name;}", ctClass);
        CtMethod setNameMethod = CtMethod.make("public void setName(String name) { this.name = name;}", ctClass);
        ctClass.addMethod(getNameMethod);
        ctClass.addMethod(setNameMethod);
        // 创建并添加构造器
        CtConstructor ctConstructor = new CtConstructor(new CtClass[]{CtClass.intType, classPool.get("java.lang.String")}, ctClass);
        ctConstructor.setBody("{this.age = $1;this.name = $2; }");
        ctClass.addConstructor(ctConstructor);
        // class文件输出
        ctClass.writeFile("target/classes/");

        // new一个实例并调用方法
        Class<?> clazz = ctClass.toClass();
        Constructor<?> constructor = clazz.getDeclaredConstructor(int.class, String.class);
        Object myUser = constructor.newInstance(18,"如花");
        Method method = clazz.getDeclaredMethod("getName");
        String name = (String)method.invoke(myUser);
        System.err.println(name);


    }
}
