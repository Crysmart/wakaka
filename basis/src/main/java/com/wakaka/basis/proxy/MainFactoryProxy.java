package com.wakaka.basis.proxy;


import java.lang.reflect.Proxy;

/**
 * 工厂代理
 * @author Crysmart
 */
public class MainFactoryProxy {
    public static void main(String[] args) {
        IFactoryMessage proxy = (IFactoryMessage)FactoryProxy.getProxy(FactoryMessage.class);
        proxy.send();
    }
}

/**
 * 工厂代理
 */
class FactoryProxy{
    public static Object getProxy(final Class<?> clz){
        return Proxy.newProxyInstance(
                clz.getClassLoader(),
                clz.getInterfaces(),
                (proxy, method, args) -> {
                    System.out.println("前置");
                    Object invoke = method.invoke(clz.getDeclaredConstructor().newInstance(), args);
                    System.out.println("后置");
                    return invoke;
                }
        );
    }
}

interface IFactoryMessage{
    void send();
}
class FactoryMessage implements IFactoryMessage{
    @Override
    public void send() {
        System.out.println("发送");
    }
}


