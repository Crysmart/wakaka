package com.wakaka.design.designs;

import java.lang.reflect.Constructor;

/**
 * 工厂设计模式
 *
 * @author wakaka
 * @date Created in 19:20 2020/4/4
 */
public class FactoryDesign {
    public static void main(String[] args) {
        IMsgService msg = Factory.getInstance(MsgServiceImpl.class);
        msg.send();
        IMsgService mgg = Factory.getInstance(MggServiceImpl.class);
        mgg.send();
    }
}

class Factory{
    public static <T> T getInstance(Class<?> clz){
        try {
            return (T) clz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
class MggServiceImpl implements IMsgService{
    public void send() {
        System.out.println("gg");
    }
}


