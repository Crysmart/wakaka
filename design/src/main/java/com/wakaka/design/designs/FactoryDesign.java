package com.wakaka.design.designs;

/**
 * 工厂设计模式
 *
 * @author wakaka
 * @date Created in 19:20 2020/4/4
 */
public class FactoryDesign {
    public static void main(String[] args) {
        IMsgService instance = Factory.getInstance(MsgServiceImpl.class);
        instance.send();
    }
}

class Factory{
    public static <T> T getInstance(Class<?> clz){
        return null;
    }
}

interface IMsgService{
    void send();
}

class MsgServiceImpl implements IMsgService{
    public void send() {
        System.out.println("发送");
    }
}


