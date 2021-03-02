package com.xn2001.sort;

import java.util.Arrays;

/**
 * 选择排序
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = {5, 9, 1, 1, 3, 556, 11, 14};
        System.out.print("整理前： ");
        System.out.println(Arrays.toString(arr));
        selectionSort(arr);
        System.out.print("整理后： ");
        System.out.println(Arrays.toString(arr));
    }

    public static void selectionSort(int[] arr) {
        int len = arr.length;
        if (len < 1) {
            return;
        }
        //外层循环表示已排序的数组
        for (int i = 0; i < len; i++) {
            //寻找未排序的数组中最小的数其坐标
            int minIndex = i;
            //内层循环表示未排序的数组
            for (int j = i; j < len; j++) {
                //当遇到较小的数，让其坐标与minIndex互换，因此在这层循环里可以找到最小的值其坐标
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }

            //找到最小的值坐标后，让其值互换位置，让未排序中的最小值提前。
            int current = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = current;
        }
    }

}
