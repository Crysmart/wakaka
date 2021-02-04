package com.wakaka.jmh.test;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * 同 Warmup注解
 * 真实迭代
 * @author Crysmart
 * @date 2021/2/4 16:24
 */
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
public class MeasurementTest {
    @Benchmark
    public static void function(){
        for (int i = 0; i < 1000000000; i++) {

        }
    }

    public static void main(String[] args) throws RunnerException {
        //基准测试：构建测试环境
        Options opts = new OptionsBuilder()
                //参与测试的类名
                .include(MeasurementTest.class.getSimpleName())
                //测试次数
                .forks(1)
                .build();
        //执行
        new Runner(opts).run();
    }
}
