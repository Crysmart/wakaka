package com.szb.platform.workflow.service;

import java.lang.reflect.InvocationTargetException;

/**
 * 跳转节点
 * @author wang.hm
 */
public interface IMoveService {
    /**
     * 对于节点跳转进行接口回调
     * 子流程
     * @param procInstId 实例id
     * @param taskDefkey 节点id
     */
    void invokeChild(String procInstId,String taskDefkey) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, InterruptedException;

    /**
     * 对于节点跳转进行接口回调
     * 主流程
     * @param procInstId 实例id
     * @param taskDefkey 节点id
     */
    void invokeMain(String procInstId,String taskDefkey) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, InterruptedException;
}
