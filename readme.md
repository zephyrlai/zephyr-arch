## 15. 

#### 1.代理模式复习
1. 静态代理
1. 动态代理（基于JDK）

#### 1.AOP的主要角色：
1. PointCut(切点): 需要被增强的部分（通常是纯粹的业务逻辑）
1. Advice(增强)： 织入切点的代码（通常无关业务，例如日志打印、连接获取与释放等）
1. Aspect(切面)：PointCut + Advice

#### 1.切点表达式：用于声明切点具体位置的表达式。通常有如下几种常用的声明方式：
1. execution：粒度是方法，表达式语法规则如下
    ```execution(modifiers-pattern ret-type-pattern declaring-type-pattern.name-pattern(param-pattern) throws-pattern)```
    * modifiers-pattern：方法的可见性，如public，protected，不限则置空（不写）；
    * ret-type-pattern：方法的返回值类型，如int，void等，不限则写通配符(*)；
    * declaring-type-pattern：方法所在类的全路径名，如com.spring.Aspect；
    * name-pattern：方法名类型，如userService()；
    * param-pattern：方法的参数类型，如java.lang.String；
    * throws-pattern：方法抛出的异常类型，如java.lang.Exception；  
    一个简单的例子如下：  
    ```execution( * cn.zephyr.aop.service..*(..))```  
    这里我们用到了2中通配符：  
        * ```*```通配符：该通配符主要用于匹配单个单词，或者是以某个词为前缀或后缀的单词。
        * ```..```通配符：该通配符表示0个或多个项，主要用于```declaring-type-pattern```（表示当前包与子包）和```param-pattern```（表示0个或多个参数）中
1. within：粒度为类（表示类下的所有方法），其参数为全路径的类名，一个简单的例子如下：  
    ```within(cn.zephyr.aop.service..*)```
1. arg：粒度为方法，匹配所有满足入参类型的方法，表达式参数是对应方法所有入参类型的全路径。一个简单的例子如下：
    ```args(java.lang.Object)```
1. @within：表示匹配带有指定注解的类（粒度为类），其使用语法如下所示：
    ```@within(annotation-type)```
1. @annotation：表示匹配使用@annotation指定注解标注的方法将会被环绕(粒度为方法)，其使用语法如下：
       ```@annotation(annotation-type)```
1. @args：表示使用指定注解标注的类作为某个方法的参数时该方法将会被匹配。
1. @DeclareParents：也称为Introduction（引入），表示为指定的目标类引入新的属性和方法。其语法规则如下：
    ``` java
    @DeclareParents(value = "TargetType", defaultImpl = WeaverType.class)
    private WeaverInterface attribute; 
    ```  
    * TargetType表示要织入的目标类型（带全路径)
    * WeaverInterface中声明了要添加的方法
    * WeaverType中声明了要织入的方法的具体实现  
    一个简单的用例如下：
        

#### 1.advice的类型（基于注解）
1. @Before：前置增强，该注解标注的方法在业务模块代码执行之前执行，其不能阻止业务模块的执行，除非抛出异常；
1. @AfterReturning：执行后增强，该注解标注的方法在业务模块代码执行之后执行；
1. @AfterThrowing：异常处理，该注解标注的方法在业务模块抛出指定异常后执行；
1. @After：后置增强，该注解标注的方法在所有的Advice执行完成后执行，无论业务模块是否抛出异常，类似于finally的作用；
1. @Around：环绕增强该注解功能最为强大，其所标注的方法用于编写包裹业务模块执行的代码，其可以传入一个ProceedingJoinPoint用于调用业务模块的代码，无论是调用前逻辑还是调用后逻辑，都可以在该方法中编写，甚至其可以根据一定的条件而阻断业务模块的调用；
1. @DeclareParents：其是一种Introduction类型的模型，在属性声明上使用，主要用于为指定的业务模块添加新的接口和相应的实现。
    
    
    
数据库的7种传播行为、4种隔离级别

>[Spring AOP切点表达式详解](https://my.oschina.net/zhangxufeng/blog/1824275)
>[Spring AOP 切点(pointcut)表达式](https://blog.51cto.com/5914679/2092253)