package com.wakaka.jmh.test;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * 添加组测试
 * 可以分组执行测试，通过include设置
 * @author Crysmart
 * @date 2021/2/4 17:17
 */
public class GroupTest {
    @Benchmark
    @Group("g1")
    public static void function1(){

    }
    @Benchmark
    @Group("g1")
    public static void function2(){

    }
    @Benchmark
    @Group("g2")
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
