## 12. 垃圾收集器、JVM参数调优与性能测试工具
### 1. JVM参数配置
借用上一节提到JVM堆内存结构的图    
![](src/main/resources/images/1201.png)   
    1. -Xms:设置堆的最小空间大小(堆内存的初始化空间)。    
    1. -Xmx:设置堆的最大空间大小。  
    1. -XX:NewSize设置新生代最小空间大小。  
    1. -XX:MaxNewSize设置新生代最大空间大小。  
    1. -XX:PermSize设置永久代最小空间大小。  
    1. -XX:MaxPermSize设置永久代最大空间大小。  
    1. -Xss设置每个线程的堆栈大小（可用于扩大递归方法的递归深度）。  
案例：首先查看堆内存信息（使用如下配置```-Xmx40m -Xms10m ```），再使用大约20M的内存空间，查看已使用的内存空间是否有变化：  
```java
public class ShowJvmMemoryInfoDemo {

    public static void main(String[] args) {
        System.err.println("最大内存"+Runtime.getRuntime().maxMemory()/1024/1024+"M");
        System.err.println("可使用内存"+Runtime.getRuntime().freeMemory()/1024/1024+"M");
        System.err.println("(创建对象前)已使用内存"+Runtime.getRuntime().totalMemory()/1024/1024+"M");
        // 新创建的对象总计占用大约20M的内存空间
        for (int i = 0; i < 10; i++) {
            byte[] b = new byte[1024*1024*2];
        }
        System.out.println("(创建对象后)已使用内存"+Runtime.getRuntime().totalMemory()/1024/1024+"M");
    }
}
```    
控制台输出（可以看到"已使用内存"的已扩大到对应数值）：  
```text
最大内存36M
可使用内存8M
(创建对象前)已使用内存9M
(创建对象后)已使用内存24M
```  

### 1. 内存溢出问题
1. ```java.lang.OutOfMemoryError```与```java.lang.StackOverflowError```的区别
    1. ```java.lang.OutOfMemoryError```是堆内存溢出（实际使用的内存数值高于设定的最大堆内存数值），JVM参数层面的解决方案是调大-Xmx  
        ```java
        public class OutOfMemoryDemo {
            // 基于-Xmx10m
            public static void main(String[] args) {
                List<Object> listObject = new ArrayList<Object>();
                // 新创建的对象总计占用至少20M的内存空间，而最大内存空间是10M
                for (int i = 0; i < 10; i++) {
                    byte[] b = new byte[1024*1024*2];
                    listObject.add(b);
                }
            }
        }
        ```
        ```text
        Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        	at cn.zephyr.memory.OutOfMemoryDemo.main(OutOfMemoryDemo.java:18)
        ```
        调大```-Xmx```参数即可（当前示例中还需考虑List对象占用了较大空间）
    1. ```java.lang.StackOverflowError```是栈内存溢出，通常发生在（不合理的）递归调用中
        ```java
        public class StackOverFlowDemo {
            // 递归深度计数器
            private static Integer counter=0;
            // -Xss1m的递归深度：11641；-Xss5m的递归深度：63267
            public static void main(String[] args) {
                try {
                    count();
                } catch (Throwable t) {
        //            t.printStackTrace();
                }finally {
                    System.err.println("递归深度："+counter);
                }
            }
        
            private static void count(){
                counter++;
                count();
            }
        }
        ```  
        当设置-Xss1m时，递归深度是11641；设置-Xss5m的递归深度：63267
1. 内存溢出与内存泄漏的区别  
    Java内存泄漏就是没有及时清理内存垃圾，导致系统无法再给你提供内存资源（内存资源耗尽，就像倒水倒多了，从杯子上面溢出了来了一样。）；
    而Java内存溢出就是你要求分配的内存超出了系统能给你的，系统不能满足需求，于是产生溢出（使用过的内存空间没有被及时释放，长时间占用内存，最终导致内存空间不足，而出现内存溢出）。

### 1. 垃圾收集器与JMeter基础使用
