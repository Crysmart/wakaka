package com.wakaka.structure.list;

/**
 * @author Crysmart
 * @date 2020/11/7 22:02
 */
public interface List{
    /**
     * 数组大小
     * @return
     */
    int size();

    /**
     * 检查集合是否空
     * @return
     */
    boolean isEmpty();

    /**
     * 添加元素
     * @param e
     * @return
     */
    boolean add(int e);

    /**
     * 指定位置元素
     * @param index
     * @return
     */
    boolean remove(int index);


    /**
     * 清除集合
     */
    void clear();

    /**
     * 获取索引元素
     * @param index
     * @return
     */
    int get(int index);

    /**
     * 指定位置设置元素
     * @param index
     * @param element
     * @return
     */
    int set(int index, int element);

}
