package com.wakaka.basis.juc;

import java.time.Instant;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 创建线程池
 * @author Crysmart
 */
public class CreateThreadPool {
    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 100;
    private static final Long KEEP_ALIVE_TIME = 1L;

    public static void main(String[] args) {
        ThreadPoolExecutor executor = getThreadPoolExecutor();

        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("CurrentThread name:" + Thread.currentThread().getName() + "date：" + Instant.now());
            });
        }
        //终止线程池
        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finished all threads");
    }

    /**
     * 自定义线程池
     * @return
     */
    public static ThreadPoolExecutor getThreadPoolExecutor() {
        /**
         * public ThreadPoolExecutor(    int corePoolSize,//核心线程数
         *                               int maximumPoolSize,//最大线程数
         *                               long keepAliveTime,//
         *                               TimeUnit unit,//
         *                               BlockingQueue<Runnable> workQueue,//任务队列
         *                               ThreadFactory threadFactory,//
         *                               RejectedExecutionHandler handler//任务拒绝策略
         *                          )
         */
        return new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new NameThreadFactory("test")
        );
    }
}

class NameThreadFactory implements ThreadFactory{

    private final AtomicInteger nextId = new AtomicInteger(1);
    private final String namePrefix;


    public NameThreadFactory(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    @Override
    public Thread newThread(Runnable task) {
        String threadName = namePrefix + " [#" + nextId.getAndIncrement() + "]";
        return new Thread(null,task,threadName);
    }
}
