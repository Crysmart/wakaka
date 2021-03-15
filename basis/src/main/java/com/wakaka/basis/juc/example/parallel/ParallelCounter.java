package com.wakaka.basis.juc.example.parallel;

import com.wakaka.basis.juc.CreateThreadPool;

import java.util.concurrent.*;

/**
 * 并行任务及时响应
 * @author Crysmart
 * @date 2021/3/11 14:47
 */
public class ParallelCounter {

    final private long timeout;
    /** 多线程：完成任务计数 */
    final private CountDownLatch countDownLatch;

    final private ThreadPoolExecutor executor = CreateThreadPool.getThreadPoolExecutor();

    public ParallelCounter(int jobSize,long timeout){
        this.countDownLatch = new CountDownLatch(jobSize);
        this.timeout = timeout;
    }

    public void executorJob(Runnable runnable){
        executor.execute(()->{
            runnable.run();
            try {
                //模拟任务延迟
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();//-1 job数量
        });
    }

    /**
     * 固定时间段后执行返回，不管多线程任务做完没有
     * @return
     */
    public Boolean await(){
        try {
            return countDownLatch.await(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void shutdown(){
        executor.shutdown();
    }


    public static void main(String[] args) {
        final ParallelCounter parallelCounter = new ParallelCounter(20, 2000);
        //并发编程的时候一定要使用线程安全类，此处由HashMap修改为ConcurrentHashMap
        //HashMap::有时候count为0，总数不为20
        //ConcurrentHashMap正常
        final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>(20);

        parallelCounter.executorJob(() -> map.put("aaa1","123"));
        parallelCounter.executorJob(() -> map.put("aaa2","123"));
        parallelCounter.executorJob(() -> map.put("aaa3","123"));
        parallelCounter.executorJob(() -> map.put("aaa4","123"));
        parallelCounter.executorJob(() -> map.put("aaa5","123"));
        parallelCounter.executorJob(() -> map.put("aaa6","123"));
        parallelCounter.executorJob(() -> map.put("aaa7","123"));
        parallelCounter.executorJob(() -> map.put("aaa8","123"));
        parallelCounter.executorJob(() -> map.put("aaa9","123"));
        parallelCounter.executorJob(() -> map.put("aaa10","123"));
        parallelCounter.executorJob(() -> map.put("aaa11","123"));
        parallelCounter.executorJob(() -> map.put("aaa12","123"));
        parallelCounter.executorJob(() -> map.put("aaa13","123"));
        parallelCounter.executorJob(() -> map.put("aaa14","123"));
        parallelCounter.executorJob(() -> map.put("aaa15","123"));
        parallelCounter.executorJob(() -> map.put("aaa16","123"));
        parallelCounter.executorJob(() -> map.put("aaa17","123"));
        parallelCounter.executorJob(() -> map.put("aaa18","123"));
        parallelCounter.executorJob(() -> map.put("aaa19","123"));
        parallelCounter.executorJob(() -> map.put("aaa20","123"));


        //固定timeout返回
        System.out.println(parallelCounter.await());
        //获取当前多线程任务运行完成任务数量
        System.out.println(parallelCounter.countDownLatch);
        System.out.println(map);
        System.out.println(map.size());
        parallelCounter.shutdown();
    }

}
