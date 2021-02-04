package com.wakaka.jmh.test;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * 吞吐时间单元
 * @author Crysmart
 * @date 2021/2/4 16:56
 */
@OutputTimeUnit(TimeUnit.DAYS)
public class OutputTimeUnitTest {
    @Benchmark
    public static void function(){

    }

    public static void main(String[] args) throws RunnerException {
        //基准测试：构建测试环境
        Options opts = new OptionsBuilder()
                //参与测试的类名
                .include(OutputTimeUnitTest.class.getSimpleName())
                //测试次数
                .forks(1)
                .build();
        //执行
        new Runner(opts).run();
    }
}
