package com.wakaka.structure.list;

import com.wakaka.structure.entity.User;

import java.util.StringJoiner;

/**
 * 链表集合
 * @author Crysmart
 * @date 2020/11/17 10:19
 */
public class LinkedList<E> implements List<E> {
    public static void main(String[] args) {
        LinkedList<User> list = new LinkedList<>();
        list.add(new User("111","111"));
        list.add(new User("222","222"));
        list.add(new User("333","333"));
//        System.out.println(list.remove(1));
//        System.out.println(list.get(0));
        list.set(2,new User("444","444"));
       // System.out.println(list.toString());
    }

    private NodePoint<E> first;
    private NodePoint<E> last;
    private int size;
    public LinkedList(){
    }

    private static class NodePoint<E> {
        private NodePoint<E> prev;
        private E item;
        private NodePoint<E> next;

        NodePoint(NodePoint<E> prev,E item,NodePoint<E> next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }

        public NodePoint<E> getPrev() {
            return prev;
        }

        public void setPrev(NodePoint<E> prev) {
            this.prev = prev;
        }

        public E getItem() {
            return item;
        }

        public void setItem(E item) {
            this.item = item;
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
        //1.获取下一个元素，如first为空则必定为空
        NodePoint<E> l = last;
        //绑定当前对象元素
        NodePoint<E> newNode = new NodePoint<>(l, e, null);
        //新产生的节点永远为下一个节点
        last = newNode;
        if (l == null){
            //如果为新创建的对象，则first必定为下一个节点
            first = newNode;
        }else{
            //如果l不为空，则first存在值，则绑定l的next上
           l.next = newNode;
        }
        //新加元素后不要忘了size+1
        size++;
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
        checkIndex(index);
        NodePoint<E> node = first;
        //删除的当前元素
        NodePoint<E> del;
        if (index == 0){
            first = node.next;
            del = node;
        }else{
            //从第1个索引开始匹配，要算出的元素
            for (int i = 1; i < index; i++) {
                node = node.next;
            }
            del = node.next;
        }
        NodePoint<E> next = node.next.next;
        node.next = null;
        node.next = next;
        //每次移除的时候一定要删除index元素的引用
        //因为index元素的上一个元素next已经被绑定到next.next元素上了，所以不需要删除
        //prev清空后，index对象就会被垃圾回收了
        next.prev = null;
        size --;
        return del.item;
    }

    /**
     * 清除集合
     */
    @Override
    public void clear() {
        first = null;
        last = null;
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
        checkIndex(index);
        NodePoint<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.item;
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
        //如果index相同，直接走添加元素
        if (index == size){
            add(element);
            return true;
        }
        checkIndex(index);
        NodePoint<E> node = this.first;
        NodePoint<E> next;
        if (index == 0){
            next = node;
            first = new NodePoint<>(null,element,next);
        } else {
            for (int i = 1; i < index; i++) {
                node = node.next;
            }
            //先获取之前的两个个索引元素
            NodePoint<E> prev = node;
            NodePoint<E> current = node.next;
            //创建新的元素
            NodePoint<E> newNode = new NodePoint<>(prev, element, current);
            //上一个元素的next添加新元素
            prev.next = newNode;
            //插入current的元素位置后，之前的current元素prev应该为newNode
            current.prev = newNode;
        }
        size++;
        return true;
    }

    @Override
    public String toString() {
        NodePoint<E> nodePoint = first;
        StringJoiner sj = new StringJoiner(",", "LinkedList = [", "]");
        for (int i = 0; i < size; i++) {
                sj.add(nodePoint.item.toString());
                nodePoint = nodePoint.next;
        }
        return sj.toString();
    }

    private void checkIndex(int index) {
        if (index >= size){
            throw new ArrayIndexOutOfBoundsException("size:" + size + "    index:" + index);
        }
    }
}
