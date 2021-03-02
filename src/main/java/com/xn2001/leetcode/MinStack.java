package com.xn2001.leetcode;

import java.util.Stack;

/**
 * @author 乐心湖
 * @date 2021/2/23 20:17
 * <p>
 * 最小栈
 * https://leetcode-cn.com/problems/min-stack/
 **/
class MinStack {

    public Stack<Integer> stack;
    public Stack<Integer> minStack;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty() || minStack.peek() >= x) {
            minStack.push(x);
        }
    }

    public void pop() {
        int top1 = stack.pop();
        int top2 = minStack.peek();
        if (top1 == top2) {
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }
}
