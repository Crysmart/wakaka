package com.wakaka.design.designs.singleton.one;

/**
 * 枚举模式的单例
 * @author Crysmart
 * @date 2021/3/4 16:07
 */
public class EnumSingleton {

    private EnumSingleton(){}

    public static EnumSingleton getInstance(){
        return Singleton.SINGLE.instance;
    }

    private enum Singleton{
        /** 枚举单例媒介 */
        SINGLE;
        private final EnumSingleton instance;

        Singleton() {
            instance = new EnumSingleton();
        }
    }
}
