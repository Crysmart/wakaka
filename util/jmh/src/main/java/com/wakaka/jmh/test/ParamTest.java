package com.wakaka.jmh.test;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.math.BigInteger;

/**
 * 参数测试,配合@State注解
 * 通过设置不同参数来测试对程序性能的影响
 * 值得注意的是，如果你设置了非常多的参数，
 * 这些参数将执行多次，通常会运行很长时间。
 * 比如参数 1 M 个，参数 2 N 个，那么总共要执行 M*N 次
 * @author Crysmart
 * @date 2021/2/4 17:17
 */
@State(Scope.Thread)
public class ParamTest {
    @Param({"1","100","10000"})
    public int arg;
    @Benchmark
    public BigInteger function1(){
        return BigInteger.valueOf(arg);
    }

    public static void main(String[] args) throws RunnerException {
        //基准测试：构建测试环境
        Options opts = new OptionsBuilder()
                //测试分组
                .include(ParamTest.class.getSimpleName())
                //测试次数
                .forks(1)
                .build();
        //执行
        new Runner(opts).run();
    }
}