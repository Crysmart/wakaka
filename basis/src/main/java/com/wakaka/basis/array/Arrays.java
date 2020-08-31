package com.wakaka.basis.array;

import com.wakaka.basis.reflect.JavaBean;

import java.text.NumberFormat;
import java.util.Date;

/**
 * 数组相关
 *
 * @author wakaka
 * @date Created in 22:23 2020/3/25
 */
public class Arrays {

    /**
     * 冒泡排序:数值交换
     * 内循环控制数值交换，每一次外层循环都会使内层循环-1
     * 外循环控制循环次数
     */
    public static int[] bubbleSort(int[] ints) {
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
