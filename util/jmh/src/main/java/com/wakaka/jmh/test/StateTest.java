package com.wakaka.jmh.test;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * 声明状态
 * Benchmark ：表示变量的作用范围是某个基准测试类。
 * Thread ：每个线程一份副本，如果配置了 Threads 注解，则每个 Thread 都拥有一份变量，它们互不影响。
 * Group ：联系上面的 @Group 注解，在同一个 Group 里，将会共享同一个变量实例。
 * @author Crysmart
 * @date 2021/2/4 17:17
 */
@State(Scope.Thread)
public class StateTest {
    @Benchmark
    public static void function1(){

    }
    @Benchmark
    public static void function2(){

    }
    @Benchmark
    public static void function3(){

    }

    public static void main(String[] args) throws RunnerException {
        //基准测试：构建测试环境
        Options opts = new OptionsBuilder()
                //测试分组
                .include("g2")
                //测试次数
                .forks(1)
                .build();
        //执行
        new Runner(opts).run();
    }
}
