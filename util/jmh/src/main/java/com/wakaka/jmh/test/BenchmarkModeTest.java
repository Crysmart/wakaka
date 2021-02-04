package com.wakaka.jmh.test;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * 多维度统计测试
 * Mode：多种方式
 * Throughput： 整体吞吐量，比如 QPS，单位时间内的调用量等；
 * AverageTime： 平均耗时，指的是每次执行的平均时间。如果这个值很小不好辨认，可以把统计的单位时间调小一点；
 * SampleTime： 随机取样，这和我们在第一课时里聊到的 TP 值是一个概念；
 * SingleShotTime： 如果你想要测试仅仅一次的性能，比如第一次初始化花了多长时间，就可以使用这个参数，其实和传统的 main 方法没有什么区别；
 * All： 所有的指标，都算一遍，你可以设置成这个参数看下效果。
 * @author Crysmart
 * @date 2021/2/4 16:33
 */
@BenchmarkMode(Mode.AverageTime)
public class BenchmarkModeTest {
    @Benchmark
    public static void function(){

    }

    public static void main(String[] args) throws RunnerException {
        //基准测试：构建测试环境
        Options opts = new OptionsBuilder()
                //参与测试的类名
                .include(BenchmarkModeTest.class.getSimpleName())
                //测试次数
                .forks(1)
                .build();
        //执行
        new Runner(opts).run();
    }
}
