package com.wakaka.jmh.test;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * 添加线程来执行测试
 * 并不是说开的线程越多越好。
 * 线程多了，操作系统就需要耗费更多的时间在上下文切换上，造成了整体性能的下降
 * @author Crysmart
 * @date 2021/2/4 16:33
 */
@Threads(2)
public class ThreadsTest {
    @Benchmark
    public static void function(){

    }

    public static void main(String[] args) throws RunnerException {
        //基准测试：构建测试环境
        Options opts = new OptionsBuilder()
                //参与测试的类名
                .include(ThreadsTest.class.getSimpleName())
                //测试次数
                .forks(1)
                .build();
        //执行
        new Runner(opts).run();
    }
}
