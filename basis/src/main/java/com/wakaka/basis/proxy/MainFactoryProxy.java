package com.wakaka.basis.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 工厂代理
 * @author Crysmart
 */
public class MainFactoryProxy {
    public static void main(String[] args) {
        IFactoryMessage instance = FactoryProxy.getInstance(FactoryMessage.class);
        instance.send();
    }
}

/**
 * 工厂代理
 */
class FactoryProxy implements InvocationHandler {
    private Object target;
    private Object bind;
    public FactoryProxy(){}
    public FactoryProxy(Object target){
        this.target = target;
        this.bind = Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this
        );
    }
    public static <T> T getInstance(Class<?> clz) {
        try {
            return (T) clz.getDeclaredConstructor().newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(proxy,args);
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


