package cn.zephyr.future;

import java.util.concurrent.*;

/**
 * @ClassName: CallableFutureDemo
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-05 15:20
 */
public class CallableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> submit = executorService.submit(new CallableThread());
        System.err.println("主线程开始执行。。。");
        System.err.println("主线程正在做其他事情。。。");
        String s = submit.get();
        System.err.println("获取到子线程的执行结果："+s);
    }
}

class CallableThread implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.err.println("子线程开始执行。。。");
        Thread.sleep(2000);
        return "哈哈，callable";
    }
}
