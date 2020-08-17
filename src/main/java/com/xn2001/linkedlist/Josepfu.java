package com.xn2001.linkedlist;

/**
 * @author 乐心湖
 * @date 2020/8/17 15:10
 **/
public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);// 加入 5 个小孩节点
        circleSingleLinkedList.showBoy();
        //测试一把小孩出圈是否正确
        circleSingleLinkedList.countBoy(1, 2, 5);
        // 2->4->1->5->3 }
    }
}

class CircleSingleLinkedList {
    //创建一个first节点
    private Boy first = null;

    //添加小孩节点，构成一个环形链表
    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("nums 的值不正确");
            return;
        }
        //辅助指针，帮助构建环形链表
        Boy curBoy = null;
        for (int i = 1; i <= nums; i++) {
            //根据编号创建小孩节点
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.setNext(first);
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    // 遍历当前的环形链表
    public void showBoy() {
        // 判断链表是否为空
        if (first == null) {
            System.out.println("没有任何小孩~~");
            return;
        }
        // 辅助指针
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号 %d \n", curBoy.getNo());
            if (curBoy.getNext() == first) {
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    // 根据用户的输入，计算出小孩出圈的顺序

    /**
     * @param startNo  表示从第几个小孩开始
     * @param countNum 表示数几下
     * @param nums     表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误， 请重新输入");
            return;
        }
        // 创建辅助指针,帮助完成小孩出圈
        Boy helper = first;
        // 将helper指针移到末尾
        while (helper.getNext() != first) {
            helper = helper.getNext();
        }
        // 定位到第startNo个孩子
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        // 小孩开始报数
        while (helper != first) {
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("小孩%d 出圈\n", first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号%d \n", first.getNo());
    }
}

class Boy {
    private int no;// 编号
    private Boy next; // 指向下一个节点,默认 null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }


    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
