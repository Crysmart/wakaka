package com.wakaka.basis.array;

/**
 * 数组相关
 *
 * @author Wang.hm
 * @date Created in 22:23 2020/3/25
 */
public class Arrays {

    /** 冒泡排序:数值交换 */
    public static int[] BubbleSort(int[] ints) {
        for (int j = 0; j < ints.length; j++) {
            for (int i = 0; i < ints.length - j - 1; i++) {
                if (ints[i] > ints[i + 1]) {
                    int temp = ints[i];
                    ints[i] = ints[i + 1];
                    ints[i + 1] = temp;
                }
            }
        }
        return ints;
    }

}
