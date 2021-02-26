package com.xn2001.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {5, 9, 1, 1, 3, 556, 11, 14};
        System.out.print("整理前： ");
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.print("整理后： ");
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            // 标识变量，表示是否进行过交换
            boolean flag = true;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = false;
                }
                System.out.println(Arrays.toString(arr));
            }
            // 在一趟排序中，一次交换都没有发生过
            if (flag) {
                System.out.println("一共仅执行了" + i + "次");
                break;
            }
        }
    }
}
