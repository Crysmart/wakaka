package com.wakaka.structure.list;

/**
 * 链表集合，自有创建
 */
public class LinkedSelfList<E> implements List<E> {

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public boolean set(int index, E element) {
        return false;
    }
}
