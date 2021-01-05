package com.wakaka.design.designs.factory.one;

/**
 * 工厂模式
 * @author Crysmart
 * @date 2021/1/5 16:54
 */
public class Starter {
    public static void main(String[] args) {
        IMsgService msg = Factory.getInstance(MsgServiceImpl.class);
        msg.send();
        IMsgService mgg = Factory.getInstance(MggServiceImpl.class);
        mgg.send();
    }
}
