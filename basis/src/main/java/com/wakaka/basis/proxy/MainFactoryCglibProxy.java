package com.wakaka.basis.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 工厂代理cglib
 * @author Crysmart
 */
public class MainFactoryCglibProxy {
    public static void main(String[] args) {
        IFactoryWork proxy = (IFactoryWork) FactoryCglibProxy.getProxy(FactoryWork.class);
        if (proxy != null) {
            proxy.work();
        }
    }
}

interface IFactoryWork{
    void work();
}
class FactoryWork implements IFactoryWork{
    @Override
    public void work() {
        System.out.println("work");
    }
}

class FactoryCglibProxy implements MethodInterceptor{
    public static Object getProxy(Class<?> clz){
        try {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(clz);
            enhancer.setCallback(new FactoryCglibProxy());
            return enhancer.create();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        try {
            System.out.println("before");
            return methodProxy.invokeSuper(obj, args);
        } finally {
            System.out.println("after");
        }
    }
}


