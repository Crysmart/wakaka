package com.wakaka.basis.juc;

import java.util.concurrent.ThreadFactory;

/**
 * 创建线程池
 * @author Crysmart
 */
public class CreateThreadPool {
}

class NameThreadFactory implements ThreadFactory{
    @Override
    public Thread newThread(Runnable r) {
        return null;
    }
}
