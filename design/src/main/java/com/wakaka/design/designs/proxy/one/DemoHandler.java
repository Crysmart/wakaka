package com.wakaka.design.designs.proxy.one;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DemoHandler implements InvocationHandler {

    private Object handler;

    public DemoHandler(Object handler){
        this.handler = handler;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before...");
        Object invoke = method.invoke(handler, args);
        System.out.println("after...");
        return invoke;
    }

    public static void main(String[] args) throws InterruptedException {
        UserController userController = new UserController();
        DemoHandler demoProxy = new DemoHandler(userController);

        IUserController proxy = (IUserController)Proxy.newProxyInstance(
                userController.getClass().getClassLoader(),
                userController.getClass().getInterfaces(),
                demoProxy
                );
        proxy.login();
    }
}
