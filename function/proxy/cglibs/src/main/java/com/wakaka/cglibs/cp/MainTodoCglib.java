package com.wakaka.cglibs.cp;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 基于类的cglib动态代理
 * @author Crysmart
 */
public class MainTodoCglib {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Work.class);
        enhancer.setCallback(new WorkProxy());
        IWork o = (IWork) enhancer.create();
        o.work();
    }
}

interface IWork {
    void work();
}
class Work implements IWork{
    @Override
    public void work() {
        System.out.println("上班啊");
    }
}

class WorkProxy implements MethodInterceptor{
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        return methodProxy.invokeSuper(o,objects);
    }
}
