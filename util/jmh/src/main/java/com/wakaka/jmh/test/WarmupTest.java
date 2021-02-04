package com.wakaka.jmh.test;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * 预热
 * iterations: 预热次数
 * time: 间隔时间
 * timeUnit: 时间单元
 * 描述为：预热三次，每次间隔一秒
 * @author Crysmart
 * @date 2021/2/4 16:02
 */
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
public class WarmupTest {


    @Benchmark
    public static void function(){
        for (int i = 0; i < 1000000000; i++) {
            
        }
    }

    public static void main(String[] args) throws RunnerException {
        //基准测试：构建测试环境
        Options opts = new OptionsBuilder()
                //参与测试的类名
                .include(WarmupTest.class.getSimpleName())
                //测试次数
                .forks(1)
                .build();
        //执行
        new Runner(opts).run();
    }
}
