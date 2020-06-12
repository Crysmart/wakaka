package com.wakaka.basis.juc;

import java.util.concurrent.Callable;

/**
 * 线程创建方式
 *
 * @author Wang.hm
 * @date Created in 22:19 2020/4/29
 */
public class CreateThread {
    public static void main(String[] args) throws Exception {
       new ThreadRun().start();
       new ThreadRun().start();
    }
}

class TreadRun implements Runnable{
    private Integer ticket = 5000;
    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            if (ticket >= 0){
                System.out.println("当前票数："+ticket--);
            }
        }
    }
}

class CallAbleRun implements Callable<String>{
    private Integer ticket = 5000;
    @Override
    public String call() throws Exception {
        for (int i = 0; i < 5000; i++) {
            if (ticket >= 0){
                System.out.println("当前票数："+ticket--);
            }
        }
        return null;
    }
}

class ThreadRun extends Thread{
    private Integer ticket = 5000;
    @Override
    public void run()  {
        for (int i = 0; i < 5000; i++) {
            if (ticket >= 0){
                System.out.println("当前票数："+ticket--);
            }
        }
    }
}
