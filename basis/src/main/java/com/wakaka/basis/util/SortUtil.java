package com.wakaka.basis.util;

import java.util.Arrays;
import java.util.Random;

public class SortUtil {
    public static void main(String[] args) {
        int len = 10;
        int maxInt = 1000;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = new Random().nextInt(maxInt);
        }
        sortBubbling(arr);
        System.out.println(Arrays.toString(arr));

    }
    /** 冒泡排序  **/
    public static void sortBubbling(int[] arr){
        int arrLen = arr.length;
        for (int i = 0; i < arrLen; i++) {
            for (int j = 0; j < arrLen -i -1; j++) {
                if (arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
}
