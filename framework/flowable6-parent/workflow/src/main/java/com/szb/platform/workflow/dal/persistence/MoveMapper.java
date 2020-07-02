package com.szb.platform.workflow.dal.persistence;

import com.szb.platform.commons.tools.tkmapper.TkMapper;
import com.szb.platform.workflow.dal.entity.ChildInvoke;
import com.szb.platform.workflow.dal.entity.MainInvoke;
import com.szb.platform.workflow.dal.entity.Move;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 跳转节点
 * @author wang.hm
 */
@Mapper
public interface MoveMapper extends TkMapper<Move> {

    /**
     * 需要调用的方法集，子流程
     * @param procInstId 实例id
     * @param taskDefKey 需要调用到的节点id
     * @return List
     */
    List<ChildInvoke> invokeChild(@Param("procInstId") String procInstId,
                                  @Param("taskDefKey") String taskDefKey);

    /**
     * 需要调用的方法集，主流程
     * @param procInstId 实例id
     * @param taskDefKey 需要调用到的节点id
     * @return List
     */
    List<MainInvoke> invokeMain(@Param("procInstId") String procInstId,
                                @Param("taskDefKey") String taskDefKey);

    /**
     * 删除子流程调用过节点
     * @param id
     */
    void delChildInvokeById(Long id);

    /**
     * 删除主流程调用过节点
     * @param id
     */
    void delMainInvokeById(Long id);
}
