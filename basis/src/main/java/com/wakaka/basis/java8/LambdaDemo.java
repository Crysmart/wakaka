package com.wakaka.basis.java8;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * @author Crysmart
 */
public class  LambdaDemo {
    public static void main(String[] args) {
        //1.常规：方法中使用接口实现的参数
        //Work.function(s -> System.out.println(s.length()));
        //2.单行调用
        Runnable runnable = () -> System.out.println("666");
        new Thread(runnable).start();
        //3.多行调用
        Runnable runnable2 = () -> {
            System.out.println("666");
        };
        //4.结果导出
        BinaryOperator<Integer> a = (x,y) -> x+y;
        System.out.println(a.apply(1,2));//3
        //5.结果导出：指定类型
        BinaryOperator<Integer> b = (Integer x,Integer y) -> x+y;
        System.out.println("a.apply(1,2) = " + a.apply(1, 2));
        //测试final
        Integer z = 5;
        //z = 6;
        BinaryOperator<Integer> c = (Integer x,Integer y) -> x+y+z;
        System.out.println("c.apply(1, 2) = " + c.apply(1, 2));
        //ThreadLocal工厂类Lambda
        ThreadLocal.withInitial(null);
        //使用Function函数
        Function<Integer,Integer> plusOne = i -> i+1;
        System.out.println("result.apply(5) = " + plusOne.apply(5));
    }
}

@FunctionalInterface
interface IFuncWork {
    //void work(String s);
    int add(int a,int b);
}

class Work implements IFuncWork{
    public static void function(IFuncWork iFuncWork){
        //iFuncWork.work("666");
    }


    @Override
    public int add(int a, int b) {
        return a+b;
    }
}

class Answers{
    /**
     * 问题答案
     */
    public static void main(String[] args) {
        answer();
    }
    public static void answer(){
        //1.DateFormatter类是非线程安全的。
        //使用构造函数创建一个线程安全的DateFormatter对象，并输出日期，如“01-Jan-1970
        ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal = ThreadLocal
                //通过ThreadLocal提供的函数接口创建
                .withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
        String format = simpleDateFormatThreadLocal.get().format(new Date());
        System.out.println("format = " + format);
    }
}
