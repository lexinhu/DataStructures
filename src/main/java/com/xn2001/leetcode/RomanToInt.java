package com.xn2001.leetcode;

import com.sun.xml.internal.bind.util.Which;

/**
 * @author 乐心湖
 * @date 2021/2/18 19:49
 * <p>
 * 罗马数字转整数
 * https://leetcode-cn.com/problems/roman-to-integer/
 **/

// 执行用时：4 ms, 在所有 Java 提交中击败了 99.97% 的用户
// 内存消耗：38.5 MB, 在所有 Java 提交中击败了 74.87% 的用户

public class RomanToInt {
    public int romanToInt(String s) {
        int result = 0;
        int i = 0;
        while (i < s.length()) {
            int preNum = getNum(s.charAt(i));
            if (i == s.length() - 1) {
                result += preNum;
                break;
            }
            int nextNum = getNum(s.charAt(i + 1));
            if (preNum < nextNum) {
                i++;
                result += nextNum - preNum;
            } else {
                result += preNum;
            }
            i++;
        }
        return result;
    }

    public int getNum(char ch) {
        switch (ch) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        int result = new RomanToInt().romanToInt("MCMXCIV");
        System.out.println(result);
    }
}
