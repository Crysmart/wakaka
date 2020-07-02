package com.szb.platform.workflow.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.szb.platform.workflow.dal.entity.ChildInvoke;
import com.szb.platform.workflow.dal.entity.ChildInvokeAfter;
import com.szb.platform.workflow.dal.entity.MainInvoke;
import com.szb.platform.workflow.dal.entity.MainInvokeAfter;
import com.szb.platform.workflow.dal.persistence.ChildInvokeAfterMapper;
import com.szb.platform.workflow.dal.persistence.MainInvokeAfterMapper;
import com.szb.platform.workflow.dal.persistence.MoveMapper;
import com.szb.platform.workflow.service.IMoveService;
import lombok.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 回退节点
 * 执行撤回方法
 *
 * @author Wang.hm
 */
@Service
public class MoveServiceImpl implements IMoveService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MoveServiceImpl.class);

    @Autowired
    private MoveMapper moveMapper;
    @Autowired
    private ChildInvokeAfterMapper childInvokeAfterMapper;
    @Autowired
    private MainInvokeAfterMapper mainInvokeAfterMapper;

    @Override
    public void invokeChild(String procInstId,String taskDefkey) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, InterruptedException {
        List<ChildInvoke> childInvokes = moveMapper.invokeChild(procInstId, taskDefkey);
        for (ChildInvoke childInvoke : childInvokes) {
            invokeMethod(childInvoke.getInvokeClass(), childInvoke.getInvokeMethod(), childInvoke.getProcInstId());
            moveMapper.delChildInvokeById(childInvoke.getId());
            childInvokeAfterMapper.insert((ChildInvokeAfter) childInvoke);
        }
    }

    @Override
    public void invokeMain(String procInstId, String taskDefkey) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, InterruptedException {
        List<MainInvoke> mainInvokes = moveMapper.invokeMain(procInstId, taskDefkey);
        for (MainInvoke mainInvoke : mainInvokes) {
            //主线判定调用
            if (!StringUtils.isEmpty(mainInvoke.getInvokeClass()) && !StringUtils.isEmpty(mainInvoke.getInvokeMethod())) {
                invokeMethod(mainInvoke.getInvokeClass(), mainInvoke.getInvokeMethod(), mainInvoke.getProcInstId());
                moveMapper.delMainInvokeById(mainInvoke.getId());
                mainInvokeAfterMapper.insert((MainInvokeAfter) mainInvoke);
            }
            //需要子流程判定调用
            if (!StringUtils.isEmpty(mainInvoke.getChildTaskDefKey()) && !StringUtils.isEmpty(mainInvoke.getChildProcInstId())) {
                this.invokeChild(mainInvoke.getChildProcInstId(), mainInvoke.getChildTaskDefKey());
            }
        }
    }

    /**
     * 反射调用类方法
     * @param invokeClass 类全限定名
     * @param invokeMethod 方法名
     * @param procInstId 实例id
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws InterruptedException
     */
    private void invokeMethod(String invokeClass, String invokeMethod, String procInstId) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, InterruptedException {
        Class<?> aClass = Class.forName(invokeClass);
        Method method = aClass.getMethod(invokeMethod, String.class);
        method.invoke(aClass.getConstructor().newInstance(), procInstId);
        LOGGER.info("节点回退调用invoke:[{}]", method.toString());
        Thread.sleep(50);
    }

}
