package com.wakaka.basis.juc.example.simpledateformat;

import com.wakaka.basis.juc.CreateThreadPool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Crysmart
 * @date 2021/3/5 14:08
 */
public class TestSimpleDateFormat {

    ThreadLocal<SimpleDateFormat> threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    public static void main(String[] args) {
        final TestSimpleDateFormat testSimpleDateFormat = new TestSimpleDateFormat();
        ThreadPoolExecutor threadPoolExecutor = CreateThreadPool.getThreadPoolExecutor();
        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.submit(()->{
                try {
                    System.out.println(testSimpleDateFormat.threadLocal.get().parse("2020-12-12 12:13:14"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPoolExecutor.shutdown();
    }
}
