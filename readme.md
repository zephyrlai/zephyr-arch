## 20. 手写基于注解的简易ORM框架
### 01. 基于JDK Proxy的动态代理(dynamic proxy)与面向切面编程(Aspect Oriented Programming,AOP)
#### 动态代理(以JDK Proxy为例)
  在代码运行时创建动态代理对象，代理对象 __通常__ 会持有被代理对象，业务代码实际持有的是动态代理对象(与被代理对象实现(继承)同一个父类(接口))。代理类中会要求实现InvocationHandler的
invoke()方法，而在这个方法中，通常会调用被代理对象的method.invoke()方法(正在的业务逻辑)，而在此之前或之后，我们就可以实现一些业务无关的功能（日志打印、性能记录等）。  
#### 面向切面编程（非Spring AOP）
在这里讲解一种简单可行的方案：在动态代理中，把接口作为代理对象，可以在invoke方法中主动实现对应的方法（组装来自方法注解、配置文件的业务），实现面向接口编程。
#### 代码示例： 
```java
public class MyInvocationHandler implements InvocationHandler {

//    private Object target;

    /*public MyInvocationHandler(Object target) {
        this.target = target;
    }*/

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.err.println("==动态代理---方法拦截==");
        return 1;
    }
}
```
```java
public interface UserMapper {
    int insertSelective();
}
```
```java
public class MyProxyMain {

    public static void main(String[] args) {
        UserMapper userMapper = (UserMapper) Proxy.newProxyInstance(
                UserMapper.class.getClassLoader(),
                new Class[]{UserMapper.class},
                new MyInvocationHandler()
        );
        int i = userMapper.insertSelective();
        System.err.println("sql返回结果："+i);
    }

}
```
控制台输出：
```text
==动态代理---方法拦截==
sql返回结果：1
```
### 02. 实现基于注解的简易ORM框架