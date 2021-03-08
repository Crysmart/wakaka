package com.wakaka.cglibs.cp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 基于接口的动态代理
 * @author Crysmart
 */
public class MainTodo {
    public static void main(String[] args) {
        MessageProxy messageProxy = new MessageProxy(new Message());
        IMessage bindProxy = (IMessage) messageProxy.getBindProxy();
        bindProxy.send();
    }
}

interface IMessage{
    /** 发送消息 */
    void send();
}
class Message implements IMessage{
    @Override
    public void send() {
        System.out.println("发送消息");
    }
}

/**
 * 代理对象
 */
class MessageProxy implements InvocationHandler {
    /** 真实目标对象 */
    private Object target;
    /** 代理实例 */
    private Object bindProxy;
    public MessageProxy(){}
    public MessageProxy(Object target){
        this.target = target;
        this.bindProxy = Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this
        );
    }

    public Object getBindProxy() {
        return bindProxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(this.target,args);
    }
}



