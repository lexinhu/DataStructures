package com.xn2001.sort;

import java.util.Arrays;

/**
 * 插入排序
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = {5, 9, 1, 1, 3, 556, 11, 14};
        System.out.print("整理前： ");
        System.out.println(Arrays.toString(arr));
        insertionSort(arr);
        System.out.print("整理后： ");
        System.out.println(Arrays.toString(arr));
    }

    public static void insertionSort(int[] arr) {
        int len = arr.length;
        if (len <= 1) {
            return;
        }
        for (int i = 1; i < len; i++) {
            // 取出当前序列中未排序的元素
            int current = arr[i];
            // 在有序区间从后往前扫描
            int preIndex = i - 1;
            while (preIndex >= 0 && arr[preIndex] > current) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = current;
        }
    }
}
