## 02. Java内存模型
1. 引入：火车票抢票问题（相关代码放在railway包下）
    1. 线程不安全的情况：售票方法未加锁，会出现重复售票、多售票的情况
        ```java
        public class ThreadNotSafe {
            public static void main(String[] args) {
                Thread01 thread1 = new Thread01();
                Thread thread01 = new Thread(thread1,"窗口1");
                Thread thread02 = new Thread(thread1,"窗口2");
                thread01.start();
                thread02.start();
            }
        }
        class Thread01 implements  Runnable{
        
            private Integer count=10;
        
            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while(count>0)
                    sale();
            }
        
            private void sale(){
                // 有必要在此处进行判断
                if(count>0)
                    System.err.println(Thread.currentThread().getName()+",当前出售——"+(10-count--+1) );
            }
        }
        ```  
        ![](src/main/resources/images/0201.png)  
   1. 线程安全的情况：给售票的方法加锁，有且只能单条线程访问售票方法  
        ``` java
            private synchronized void sale(){
                // 有必要在此处进行判断
                if(count>0)
                    System.err.println(Thread.currentThread().getName()+",当前出售——"+(10-count--+1) );
            }
        ```  
        ![](src/main/resources/images/0202.png)  
1. 什么是线程安全问题：  
    多个线程共享一个全局变量，在进行对该变量进行写操作的时候，可能会引发线程安全问题（读操作不会引发）
1. 如何解决多线程之间线程安全问题:  
    使用多线程之间同步(synchronized，内置锁，自动挡)或使用锁(lock，手动挡)。这2种机制能让当前唯一个线程进行执行。代码执行完成后释放锁，然后才能让其他线程进行执行。这样的话就可以解决线程不安全问题。
1. synchronized的3种使用方式(相关代码放在sync包下)
    1. 同步代码块：注意各线程实例一定要持有同一个锁对象（不能持有各自的锁对象）
    1. 非静态同步方法：任意对象作为锁
    1. 静态同步方法：字节码文件（class文件）作为锁
1. 死锁(相关代码放在deadlock包下)   
    1. 同步(synchronized)中嵌套同步(synchronized)，多个线程互相持有对方的锁，并同时等待对方释放锁，导致锁无法释放；
    1. 在实际使用中一定要注意避免同步(synchronized)互相嵌套的情况
    1. (补充)重入锁：可重复可递归调用的锁，在外层使用锁之后，在内层仍然可以使用，并且不发生死锁（这里的锁指的是同一个锁对象）；synchronized锁是重入锁
    1. 代码示例： 
        ```java
        public class DeadLockThread {
            public static void main(String[] args) {
                Thread1 thread1 = new Thread1();
                Thread thread01 = new Thread(thread1);
                Thread thread02 = new Thread(thread1);
                thread01.start();
                thread02.start();
            }
        }
        
        class Thread1 implements Runnable{
            private Integer count=100;
            
            private final Object obj= new Object();
        
            @Override
            public void run() {
                while(count>0){
                    if(count%2==0){
                        // 重入锁
                        // 先持有obj锁，后持有this锁
                        synchronized (obj){
                            sale();
                        }
                    }else{
                        // 先持有this锁，后持有obj锁
                        sale();
                    }
                }
            }
        
            private synchronized void sale(){
                synchronized (obj){
                    System.err.println(Thread.currentThread().getName()+"--方法--"+(100-count+1));
                    count--;
                }
            }
        }
        ```  
        ![](src/main/resources/images/0204.png)  
1. ThreadLocal（相关代码放在threadlocal包下）
    1. ThreadLocal是一个关于创建线程局部变量的类。通常情况下，我们创建的变量是可以被任何一个线程访问并修改的。而使用ThreadLocal创建的变量只能被当前线程访问，其他线程则无法访问和修改.
    1. 代码示例：
        ```java
        public class ThreadlocalDemo {
            public static void main(String[] args) {
                Thread05 thread = new Thread05();
                Thread thread1 = new Thread(thread);
                Thread thread2 = new Thread(thread);
                thread1.start();
                thread2.start();
            }
        }
        
        class Thread05 implements  Runnable{
        
            private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
                // 设定初始值
                @Override
                protected Integer initialValue() {
                    return 0;
                }
            };
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    System.err.println(Thread.currentThread().getName()+"----"+getCount());
                }
            }
        
            private synchronized Integer getCount(){
                Integer count = threadLocal.get();
                threadLocal.set(++count);
                return count;
            }
        }
        ```  
        ![](src/main/resources/images/0205.png)  
    1. 实际上ThreadLocal的值是放入了当前线程的一个ThreadLocalMap实例中，key是当前线程，value是对应值
1. 多线程的3大特性
    1. 原子性（线程安全问题）：一个完整的操作可能会包含多个步骤，要么所有步骤全部执行，要么就一个步骤都不执行，不能出现一个操作执行到一半被打断的情况；
    1. 可见性（volatile）：当多个线程访问同一个变量时，一个线程修改了这个变量的值，其他线程能够立即看得到修改的值；
    1. 有序性（重排序）：程序执行的顺序按照代码的先后顺序执行，处理器为了提高程序运行效率，可能会对输入代码进行优化，它不保证程序中各个语句的执行先后顺序同代码中的顺序一致，
    但是它会保证程序最终执行结果和代码顺序执行的结果是一致的。重排序对单线程运行是不会有任何问题，而多线程就不一定了。
1. Java内存模型（Java Memory Model,JMM）
    Java内存模型(简称JMM)决定一个线程对共享变量的写入时,能对另一个线程可见。JMM定义了线程和主内存之间的抽象关系：线程之间的共享变量存储在主内存（main memory）中，每个线程都有一个私有的本地内存（local memory），
    本地内存中存储了该线程以读/写共享变量的副本。当多个线程同时访问一个数据的时候，可能本地内存没有及时刷新到主内存，所以就会发生线程安全问题；
    本地内存是JMM的一个抽象概念，并不真实存在。它涵盖了缓存，写缓冲区，寄存器以及其他的硬件和编译器优化。
    ![](src/main/resources/images/0203.png)  
1. volatile关键字(相关代码放在vola包下)   ：
    1. 可见性也就是说一旦某个线程修改了该被volatile修饰的变量，它会保证修改的值会立即被更新到主存，当有其他线程需要读取时，可以立即获取修改之后的值。
    1. 在Java中为了加快程序的运行效率，对一些变量的操作通常是在该线程的寄存器或是CPU缓存上进行的，之后才会同步到主存中，而加了volatile修饰符的变量则是直接读写主存。
    1. 只能解决可见性问题，不能解决原子性问题，不能解决线程安全问题
    1. 禁止"指令重排序优化"（什么是指令重排序：是指CPU采用了允许将多条指令不按程序规定的顺序分开发送给各相应电路单元处理，旨在提高程序运行效率），volatile修饰的变量在赋值后，会额外添加一个内存屏障（指令重排序时不能把后面的指令重排序到内存屏障之前的位置）。
    1. 性能：volatile 的读性能消耗与普通变量几乎相同，但是写操作稍慢，因为它需要在本地代码中插入许多内存屏障指令来保证处理器不发生乱序执行。
    1. 代码示例：
        ```java
        public class VolatileDemo {
            public static void main(String[] args) throws InterruptedException {
                Thread06 thread06 = new Thread06();
                Thread thread = new Thread(thread06);
                thread.start();
                Thread.sleep(2000);
                thread06.setFlag(true);
                Thread.sleep(2000);
            }
        }
        
        class Thread06 implements Runnable{
            // 不加volatile则程序无法停止
            private volatile Boolean flag = false;
        
            public void setFlag(Boolean flag) {
                this.flag = flag;
            }
        
            @Override
            public void run() {
                System.err.println("线程开始执行 ==》");
                while(!flag){}
                System.err.println("线程结束执行《==");
            }
        }   
        ```  
    ![](src/main/resources/images/0206.png)    
> todo：  
synchronized底层 实现原理（monitor）  
JVM内存结构：堆、栈、方法区、寄存器