## 13. JVM字节码技术
### 1. 字节码技术
#### 应用场景
AOP技术、Lombok代码插件、CGLIB动态代理库等
#### 优势
Java字节码增强指的是在Java字节码生成之后，对其进行修改，增强其功能，这种方式相当于对应用程序的二进制文件进行修改。Java字节码增强主要是为了减少冗余代码，提高性能等。
实现字节码增强的主要步骤为：
1、修改字节码
   在内存中获取到原来的字节码，然后通过一些工具（如 ASM，Javaasist）来修改它的byte[]数组，得到一个新的byte数组。
2、使修改后的字节码生效（2种方法）：
    1. 自定义ClassLoader来加载修改后的字节码；
    2. 替换掉原来的字节码：在JVM加载用户的Class时，拦截，返回修改后的字节码；或者在运行时，使用Instrumentation.redefineClasses方法来替换掉原来的字节码
#### 常见的字节码操作类库
1. Byte Code Engineering Library(BCEL)：是Java classworking 广泛使用的一种框架，它可以让我们深入jvm汇编语言进行类库操作的细节。（汇编层面，暂略）
1. ASM：是一个轻量级Java字节码操作框架，直接涉及到JVM底层的操作和指令。高性能，高质量。
1. javassist：是一个开源的分析，编辑和创建Java字节码的类库。性能较ASM差，跟cglib差不多，但是使用简单。很多开源框架都在使用它。
    1. 优势  
        1. 比反射开销小，性能高。
        1. 性能高于反射，低于ASM
    1. 运行时操作字节码可以让我们实现如下功能：
        1. 动态生成 新的类
        1. 动态改变某个类的结构 ( 添加 / 删除 / 修改    新的属性 / 方法 )
    1.javassist 的最外层的 API 和 JAVA 的反射包中的 API 颇为 类似 。它主要由CtClass，CtMethod以及CtField几个类组成。
    用以执行和 JDK 反射 API 中 java.lang.Class， java.lang.reflect.Method， java.lang.reflect.Method .Field 相同的 操作 。
    1. 能够实现的功能：
        1. 修改已有方法的方法体（插入代码到已有方法体）
        1. 新增方法   
        1. 删除方法
    1. 局限性：
        1. JDK5.0 新语法不支持 ( 包括泛型、枚举 ) ，不支持注解修改，但可以通过底层的 javassist 类来解决，具体参考： javassist.bytecode.annotation
        1. 不支持数组的初始化，如 String[]{"1","2"} ，除非只有数组的容量为 1
        1. 不支持内部类和匿名类
        1. 不支持 continue 和 break表达式。
        1. 对于继承关系，有些不支持。例如：
            ``` java
            class A {}  
            class B extends A {} 
            class C extends B {}
            ```
#### 案例：动态生成类
代码： 
```java
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
```  
控制台输出：  
```text
如花
```
#### 案例：为现有类添加方法
代码： 
```java
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
```
控制台输出：  
```text
33
```
### 2. 其他知识点
1. JDK可视化工具（查看系统开销、线程数等信息）
    1. jconsole（jdk自带，位于bin目录）
    1. visualVm（同属oracle）
### 3. 参考
> [AOP 的利器：ASM 3.0 介绍](https://www.ibm.com/developerworks/cn/java/j-lo-asm30/index.html)  
> [字节码操纵技术探秘](https://www.infoq.cn/article/Living-Matrix-Bytecode-Manipulation)