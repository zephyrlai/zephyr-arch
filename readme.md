

1. 代理模式复习
    1. 静态代理
    1. 动态代理
AOP的主要角色：
1. PointCut(切点): 需要被增强的部分（通常是纯粹的业务逻辑）
1. Advice(增强)： 织入切点的代码（通常无关业务，例如日志打印、连接获取与释放等）
1. Aspect(切面)：PointCut + Advice

切点表达式：用于声明切点具体位置的表达式。通常有如下几种声明方式：
1. execution
1. within
1. arg：
1. this/target：

advice的类型（基于注解）
1. @Before：前置增强，该注解标注的方法在业务模块代码执行之前执行，其不能阻止业务模块的执行，除非抛出异常；
1. @AfterReturning：执行后增强，该注解标注的方法在业务模块代码执行之后执行；
1. @AfterThrowing：异常处理，该注解标注的方法在业务模块抛出指定异常后执行；
1. @After：后置增强，该注解标注的方法在所有的Advice执行完成后执行，无论业务模块是否抛出异常，类似于finally的作用；
1. @Around：环绕增强该注解功能最为强大，其所标注的方法用于编写包裹业务模块执行的代码，其可以传入一个ProceedingJoinPoint用于调用业务模块的代码，无论是调用前逻辑还是调用后逻辑，都可以在该方法中编写，甚至其可以根据一定的条件而阻断业务模块的调用；
1. @DeclareParents：其是一种Introduction类型的模型，在属性声明上使用，主要用于为指定的业务模块添加新的接口和相应的实现。
    
    
    
数据库的7种传播行为、4种隔离级别

>[Spring AOP切点表达式详解](https://my.oschina.net/zhangxufeng/blog/1824275)
>[Spring AOP 切点(pointcut)表达式](https://blog.51cto.com/5914679/2092253)