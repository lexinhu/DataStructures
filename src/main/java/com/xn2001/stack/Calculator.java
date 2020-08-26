package com.xn2001.stack;

public class Calculator {

    public static void main(String[] args) {
        String expression = "2-2*2+5";
        //创建两个栈，一个是数栈一个是符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';
        String keepNum = "";
        do {
            //依次得到expression的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch是什么，然后做相应的处理
            if (operStack.isOper(ch)) {
                if (!operStack.isEmpty()) {
                    //如果符号栈有操作符，就进行比较,如果当前的操作符的优先级小于或者等于栈中的操作符, 就需要从数栈中 pop 出两个数
                    //在从符号栈中 pop 出一个符号，进行运算，将得到结果，入数栈，然后将当前的操作符入符 号栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        numStack.push(res);
                    }
                }
                //入符号栈
                operStack.push(ch);
            } else {
                //1. 当处理多位数时，不能发现是一个数就立即入栈，因为他可能是多位数
                //2. 在处理数，需要向 expression 的表达式的 index 后再看一位,如果是数就进行扫描，如果是符号 才入栈
                //3. 因此我们需要定义一个变量 字符串，用于拼接
                //处理多位数
                keepNum += ch;
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，就入栈
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        //如果后一位是运算符，则入栈 keepNum = "1" 或者 "123"
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
            index++;
            //让 index+1，并判断是否扫描到expression最后
        } while (index < expression.length());
        //最后循环的这个流程中，只有加减法！
        while (!operStack.isEmpty()) {
            //如果符号栈为空，则计算到最后的结果，数栈中只有一个数字
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            //遇到前面是减号，那就执行减法而不是加法。
            if (!operStack.isEmpty() && operStack.peek() == '-') {
                oper = operStack.peek();
            }
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        int res2 = numStack.pop();
        System.out.printf("表达式 %s = %d", expression, res2);
    }

}

class ArrayStack2 {
    private int maxSize; //栈的大小
    private int[] stack; //数组，数组模拟栈
    private int top = -1; //栈顶，初始化为-1

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //栈顶
    public int peek() {
        return stack[top];
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    //入栈-push
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈-pop
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，没有数据~");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //显示栈的情况[遍历栈]， 遍历时，需要从栈顶开始显示数据
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    //返回运算符的优先级，优先级由程序员定义，优先级使用数字表示
    //数字越大，则优先级越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 2;
        } else if (oper == '+' || oper == '-') {
            return 1;
        } else {
            return -1; //暂时不考虑小括号
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1; //注意顺序，比如5-3 5先入栈，晚出栈，所以num2才是5
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}