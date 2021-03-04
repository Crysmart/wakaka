package com.wakaka.design.designs.singleton.one;

/**
 * 双检查单例
 * @author Crysmart
 * @date 2021/3/4 16:12
 */
public class DoubleCheckSingleton {

    private DoubleCheckSingleton(){}
    /**
     * volatile： 在一些低版本的 Java 里，由于指令重排的缘故，
     * 可能会导致单例被 new 出来后，还没来得及执行构造函数，就被其他线程使用。
     * 这个关键字，可以阻止字节码指令的重排序，
     * 在写 double check 代码时，习惯性会加上 volatile
     */
    private volatile static DoubleCheckSingleton instance;

    public static DoubleCheckSingleton getInstance(){

        //1.实例化检查
        if (null == instance){
            //2.类锁，防止并发入侵
            synchronized (DoubleCheckSingleton.class){
                //2.第二次实例化检查
                if (null == instance){
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
}
