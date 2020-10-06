package com.xn2001.recursion;

public class Queue8 {

    private int[] array = new int[8];

    private static int count = 0;

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        // 从第一行第一列开始放起
        queue8.place(0);
        System.out.println("一共有 " + count + " 种结果");
    }

    private void place(int n) {
        // 放到第8个皇后时，不用再放了，因为我们是从0开始算起，第8个就让它结束，因为已经放好了。
        if (n == 8) {
            // TODO: 2020/10/6 打印结果
            print();
            return;
        }

        // 使用循环，依次放入皇后，判断是否冲突
        for (int i = 0; i < 8; i++) {
            // 先把当前这个皇后 n , 放到该行的第 i 列
            array[n] = i;
            // TODO: 2020/10/6  判断当放置第n个皇后到i列时，是否冲突
            if (judge(n)) { // 如果不冲突的话，我们放下一个皇后
                place(n + 1);
            }
        }
    }

    // 查看当我们放置第 n 个皇后, 就去检测该皇后是否和前面已经摆放的皇后冲突
    public boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            // 1. array[i] == array[n] 表示判断 第 n 个皇后是否和前面的 n-1 个皇后在同一列
            // 2. Math.abs(n-i) == Math.abs(array[n] - array[i]) 表示判断第 n 个皇后是否和第 i 皇后是否在同一斜线
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[i] - array[n])) {
                return false;
            }
        }
        return true;
    }

    //可以将皇后摆放的位置输出
    private void print() {
        count++;
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

}
