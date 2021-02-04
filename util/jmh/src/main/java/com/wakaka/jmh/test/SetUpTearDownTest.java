package com.wakaka.jmh.test;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * 配合@State注解
 * Setup: 用于基准测试前的初始化动作
 * TearDown: 用于基准测试后的动作，来做一些全局的配置
 * LEVEL:
 *  Trial ：默认的级别，也就是 Benchmark 级别。
 *  Iteration ：每次迭代都会运行。
 *  Invocation ：每次方法调用都会运行，这个是粒度最细的。
 * @author Crysmart
 * @date 2021/2/4 17:24
 */
@State(Scope.Thread)
public class SetUpTearDownTest {
    @Benchmark
    @Setup
    public static void function1(){

    }

    @Benchmark
    @TearDown
    public static void function2(){

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
