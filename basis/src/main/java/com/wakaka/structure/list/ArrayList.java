package com.wakaka.structure.list;

import com.wakaka.structure.entity.User;

/**
 * 动态数组集合
 * @author Crysmart
 * @date 2020/11/8 16:24
 */
public class ArrayList<E> implements List<E>{

    /**
     * 数组元素量
     */
    private int size = 0;

    /**
     * 初始数组
     */
    private Object[] array = {};

    public ArrayList(int initCapacity) {
        array = new Object[initCapacity];
    }

    public ArrayList() {
        //默认容量
        final int default_capacity = 10;
        array = new Object[default_capacity];
    }

    /**
     * 数组越界检查
     */
    void checkArrayRange(int index){
        if (index > array.length - 1){
            throw new ArrayIndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    /**
     * 数组元素检查
     */
    void checkRange(int index){
        if (index > size - 1){
            throw new ArrayIndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    /**
     * 检查数组扩容
     */
    void isCapacity(){
        if (size + 1 == array.length){
            //扩容
            Object[] newArray = new Object[array.length + (array.length >> 1)];
            System.out.println("扩容："+ newArray.length);
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
    }

    /**
     * 越界消息拼装
     * @param index
     * @return
     */
    String outOfBoundsMsg(int index){
        return "Index: " + index + ", size: " + size;
    }

    public static class ArrayListTest {
        public static void main(String[] args) {
            ArrayList<User> users = new ArrayList<>(20);
            users.add(new User("111","aaa"));
            users.add(new User("222","bbb"));
            users.add(new User("333","ccc"));
            users.add(new User("444","ddd"));
            System.out.println(users.get(0));
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(E e) {
        isCapacity();
        array[size++] = e;
        return true;
    }

    @Override
    public E remove(int index) {
        checkArrayRange(index);
        Object old = array[index];
        for (int i = index; i <= size - 1; i++){
            array[i] = array[i + 1];
        }
        size--;
        return (E) old;
    }

    @Override
    public void clear() {
        int clearSize = size;
        size = 0;
        for (int i = 0; i < clearSize; i++) {
            array[i] = null;
        }
    }

    @Override
    public E get(int index) {
        return (E) array[index];
    }

    @Override
    public boolean set(int index, E element) {
        checkArrayRange(index);
        for (int i = size; i >= index ; i--){
            isCapacity();
            array[i + 1] = array[i];
        }
        array[index] = element;
        size++;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("size = [" + size + "],ArrayList[");
        for(int i = 0; i < size; i++){
            if (i != 0){
                sb.append(", ");
            }
            sb.append(array[i]);
        }
        sb.append("]");
        return sb.toString();
    }
}
