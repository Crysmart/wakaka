package com.wakaka.structure.list;

import com.wakaka.structure.entity.User;
import org.w3c.dom.Node;

/**
 * 链表集合
 * @author Crysmart
 * @date 2020/11/17 10:19
 */
public class LinkedList<E> implements List<E> {
    public static void main(String[] args) {
        LinkedList<User> objectLinkedList = new LinkedList<>();
        objectLinkedList.add(new User("111","111"));
        objectLinkedList.add(new User("222","222"));
        System.out.println(objectLinkedList.toString());
    }

    private NodePoint<E> first;
    private NodePoint<E> last;
    private int size;
    public LinkedList(){
        first = new NodePoint<>();
    }

    private class NodePoint<E> {
        private E nodeObj;
        private NodePoint<E> next;

        public E getNodeObj() {
            return nodeObj;
        }

        public void setNodeObj(E nodeObj) {
            this.nodeObj = nodeObj;
        }

        public NodePoint<E> getNext() {
            return next;
        }

        public void setNext(NodePoint<E> next) {
            this.next = next;
        }
    }

    /**
     * 数组大小
     *
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 检查集合是否空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 添加元素
     *
     * @param e
     * @return
     */
    @Override
    public boolean add(E e) {
        return true;
    }

    /**
     * 指定位置元素
     *
     * @param index
     * @return
     */
    @Override
    public E remove(int index) {
        return null;
    }

    /**
     * 清除集合
     */
    @Override
    public void clear() {
        size = 0;
    }

    /**
     * 获取索引元素
     *
     * @param index
     * @return
     */
    @Override
    public E get(int index) {
        return null;
    }

    /**
     * 指定位置设置元素
     *
     * @param index
     * @param element
     * @return
     */
    @Override
    public boolean set(int index, E element) {
        return false;
    }

    @Override
    public String toString() {
        NodePoint<E> nodePoint = first;
        StringBuilder sb = new StringBuilder("LinkedList = [");
        for (int i = 1; i <= size; i++) {
            if (i == 1){
                sb.append(nodePoint.nodeObj.toString());
            } else {
                nodePoint = nodePoint.next;
                sb.append(",");
                sb.append(nodePoint.nodeObj.toString());
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
