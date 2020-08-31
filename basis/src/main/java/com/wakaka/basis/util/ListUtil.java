package com.wakaka.basis.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Crysmart
 * @date 2020/8/31 17:48
 */
public class ListUtil {

    private Integer id;
    private Integer parentId;
    private List<ListUtil> children = new ArrayList<>();

    /**
     * 组装tree
     * @param list
     * @return
     */
    public static ArrayList<ListUtil> assTree(List<ListUtil> list){
        //节点树
        HashMap<Integer, ListUtil> orgNode = new HashMap<>();
        //将数据存储在节点树中
        for (ListUtil org : list) {
            orgNode.put(org.getId(), org);
        }

        //结构树
        ArrayList<ListUtil> vos = new ArrayList<>();
        try {
            for (ListUtil org : list) {
                ListUtil node = orgNode.get(org.getId());
                //如果为空则为父级菜单
                if (IntegerUtil.isBlank(org.getParentId())){
                    vos.add(org);
                }else{
                    //否则获取节点树中的节点，然后将该条数据添加在树节点中
                    ListUtil parent = orgNode.get(org.getParentId());
                    List<ListUtil> children = parent.getChildren();
                    children.add(node);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<ListUtil> getChildren() {
        return children;
    }

    public void setChildren(List<ListUtil> children) {
        this.children = children;
    }
}
