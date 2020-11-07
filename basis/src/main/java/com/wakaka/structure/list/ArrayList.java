package com.wakaka.structure.list;

/**
 * 动态数组集合
 * @author Crysmart
 * @date 2020/11/7 22:01
 */
public class ArrayList implements List{
    /**
     * 数组元素量
     */
    private int size = 0;
    /**
     * 默认容量
     */
    private final int DEFAULT_CAPACITY = 10;
    /**
     * 初始数组
     */
    private int[] array = new int[DEFAULT_CAPACITY];

    /**
     * 数组越界检查
     */
    void checkArrayRange(int index){
        if (index > array.length){
            throw new ArrayIndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    /**
     * 数组元素检查
     */
    void checkRange(int index){
        if (index > size){
            throw new ArrayIndexOutOfBoundsException(outOfBoundsMsg(index));
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

    /**
     * 内部方法测试类
     */
    public static class ArrayListTest {
        public static void main(String[] args) {
            ArrayList list = new ArrayList();
            list.add(11);
            list.add(22);
            list.add(33);
            list.add(44);
            list.remove(0);
            System.out.println(list);
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
    public boolean add(int e) {
        array[size] = e;
        size++;
        return true;
    }

    @Override
    public boolean remove(int index) {
        checkArrayRange(index);
        //未处理大于size的值
        for (int i = index; i <= size - 1; i++){
            array[i] = array[i + 1];
        }
        size--;
        return true;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public int get(int index) {
        checkRange(index);
        return array[index];
    }

    @Override
    public int set(int index, int element) {
        return 0;
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
