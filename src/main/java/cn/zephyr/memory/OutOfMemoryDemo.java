package cn.zephyr.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: OutOfMemoryDemo
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-20 23:39
 */
public class OutOfMemoryDemo {
    //-Xmx10m
    public static void main(String[] args) {
        List<Object> listObject = new ArrayList<Object>();
        // 新创建的对象总计占用至少20M的内存空间，而最大内存空间是10M
        for (int i = 0; i < 10; i++) {
            byte[] b = new byte[1024*1024*2];
            listObject.add(b);
        }
    }
}
