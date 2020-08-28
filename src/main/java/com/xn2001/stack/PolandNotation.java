package com.xn2001.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class PolandNotation {

    public static void main(String[] args) {

        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的List：" + infixExpressionList); //[1, +, (, (, 2, +, 3, ), *, 4, ), -, 5]

        List<String> parseSuffixExpressionList = parseSuffixExpreesionList(infixExpressionList);
        System.out.println("后缀表达式对应的List：" + parseSuffixExpressionList); //[1, 2, 3, +, 4, *, +, 5, -]

        int calculate = calculate(parseSuffixExpressionList);
        System.out.printf("expression = %d", calculate);
        /*
        //先定义给逆波兰表达式
        // (30+4)×5-6 => 30 4 + 5 × 6 - => 164
        // 4 * 5 - 8 + 60 + 8 / 2 => 4 5 * 8 - 60 + 8 2 / +
        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
        //1. 先将 "3 4 + 5 × 6 - " => 放到 ArrayList 中
        //2. 将 ArrayList 传递给一个方法，遍历 ArrayList 配合栈 完成计算
        List<String> rpnList = getListString(suffixExpression);
        System.out.println(rpnList);
        int res = calculate(rpnList);
        System.out.println("计算的结果是 " + res);
        */
    }

    //方法：将得到的中缀表达式对应的List => 后缀表达式对应的List
    public static List<String> parseSuffixExpreesionList(List<String> ls) {
        //定义两个栈
        Stack<String> s1 = new Stack<>(); //符号栈
        //说明：因为s2这个栈在整个转换过程中，没有pop操作，而且后面我们还需要逆袭输出
        //因此比较麻烦，这里我们不用Stack<String> 直接使用List<String> s2
        List<String> s2 = new ArrayList<>(); //储存中间结果的栈s2
        for (String item : ls) {
            //如果是一个数，加入s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop(); // 将 "(" 弹出,消除小括号
            } else {
                //当item的优先级小于等于栈顶运算符的优先级
                while (s1.size() != 0 && !s1.peek().equals("(") && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }

        //将s1中剩下的运算符依次弹出并加入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }

        return s2;
    }

    //方法：将 中缀表达式转换成对应的List
    public static List<String> toInfixExpressionList(String s) {
        List<String> ls = new ArrayList<>();
        int i = 0; //一个指针，用于遍历 中缀表达式字符串;
        String str; //对多位数的拼接
        char c;
        do {
            //如果c是一个非数字，需要加入到ls
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++;
            } else {
                str = ""; //先将 str 置成"" '0'[48]->'9'[57]
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;//拼接
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }

    //将一个逆波兰表达式， 依次将数据和运算符 放入到 ArrayList 中
    public static List<String> getListString(String suffixExpression) {
        //根据空格来切割
        String[] split = suffixExpression.split("\\s+");
        return new ArrayList<>(Arrays.asList(split));
    }

    /*
     * 完成对逆波兰表达式的运算
     * 1)从左至右扫描，将 3 和 4 压入堆栈；
     * 2)遇到+运算符，因此弹出 4 和 3（4 为栈顶元素，3 为次顶元素），计算出 3+4 的值，得 7，再将 7 入栈；
     * 3)将 5 入栈；
     * 4)接下来是×运算符，因此弹出 5 和 7，计算出 7×5=35，将 35 入栈；
     * 5)将 6 入栈；
     * 6)最后是-运算符，计算出 35-6 的值，即 29，由此得出最终结果
     */
    public static int calculate(List<String> ls) {
        //创建栈
        Stack<String> stack = new Stack<>();
        for (String l : ls) {
            // 匹配的是数
            if (l.matches("\\d+")) {
                stack.push(l);
            } else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                switch (l) {
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num1 - num2;
                        break;
                    case "*":
                        res = num1 * num2;
                        break;
                    case "/":
                        res = num1 / num2;
                        break;
                    default:
                        System.out.println(l.toString());
                        throw new RuntimeException("运算符有误");
                }
                //把res入栈
                stack.push(res + "");
            }
        }
        return Integer.parseInt(stack.pop());
    }
}

//编写一个类 Operation 可以返回一个运算符对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }
}