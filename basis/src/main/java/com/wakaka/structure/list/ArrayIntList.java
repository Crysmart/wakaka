package com.wakaka.structure.list;

/**
 * 动态int数组集合
 * @author Crysmart
 * @date 2020/11/7 22:01
 */
public class ArrayIntList{
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
            int[] newArray = new int[array.length + (array.length >> 1)];
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

    /**
     * 内部方法测试类
     */
    public static class ArrayListTest {
        public static void main(String[] args) {
            ArrayIntList list = new ArrayIntList();
            for (int i = 0; i < 50; i++) {
                list.add(i);
                list.set(i,i);
            }
            System.out.println(list.toString());
            list.clear();
            list.add(10);
            list.add(20);
            list.add(30);
            list.remove(0);
            System.out.println("list.toString()="+list.toString());
            System.out.println("list.isEmpty()="+list.isEmpty());
            System.out.println("list.size()="+list.size());
            System.out.println("list.get(list.size())="+list.get(list.size()-1));

        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean add(int e) {
        isCapacity();
        array[size++] = e;
        return true;
    }

    public boolean remove(int index) {
        checkArrayRange(index);
        //未处理大于size的值
        for (int i = index; i <= size - 1; i++){
            array[i] = array[i + 1];
        }
        size--;
        return true;
    }

    public void clear() {
        size = 0;
    }

    public int get(int index) {
        checkRange(index);
        return array[index];
    }

    public int set(int index, int element) {
        checkArrayRange(index);
        for (int i = size; i >= index ; i--){
            isCapacity();
            array[i + 1] = array[i];
        }
        array[index] = element;
        size++;
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
