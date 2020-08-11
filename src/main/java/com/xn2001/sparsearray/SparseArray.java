package com.xn2001.sparsearray;

/**
 * 稀疏数组
 *
 * @author 乐心湖
 * @date 2020/8/11 14:53
 **/
public class SparseArray {
    public static void main(String[] args) {
        //创建一个原始的二维数组 11 * 11
        //0: 表示没有棋子
        //1: 表示黑子
        //1: 表示蓝子
        int chessArr1[][] = new int[7][8];
        chessArr1[1][2] = 5;
        chessArr1[2][3] = 8;
        chessArr1[2][4] = 8;
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.format("%d\t", data);
            }
            System.out.println();
        }

        //将二维数组 转成 -> 稀疏数组
        //1. 先遍历数组，找出有效值个数
        int sum = 0;
        for (int[] row : chessArr1) {
            for (int data : row) {
                if (data != 0) {
                    sum++;
                }
            }
        }
        //2. 创建对应的稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        //3. 给稀疏数组首行赋值
        sparseArr[0][0] = chessArr1.length;
        sparseArr[0][1] = chessArr1[0].length;
        //4. 遍历二维数组，将非 0 的值存放到 sparseArr 中
        int count = 0; //count 用于记录是第几个非 0 数据
        sparseArr[0][2] = sum;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[0].length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        // 5. 输出稀疏数组的形式
        System.out.println();
        System.out.println("得到稀疏数组为~~~~");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.format("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }
        System.out.println();

        //将稀疏数组 -> 恢复成 原始的二维数组

        /*
         * 1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，比如上面的`chessArr2 =int[11][11]`
         * 2.在读取稀疏数组后几行的数据，并赋给原始的二维数组即可
         */

        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        System.out.println("恢复后的二维数组");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.format("%d\t", data);
            }
            System.out.println();
        }
    }
}
