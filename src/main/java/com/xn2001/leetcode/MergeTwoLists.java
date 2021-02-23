package com.xn2001.leetcode;

/**
 * @author 乐心湖
 * @date 2021/2/20 17:36
 * <p>
 * 合并两个有序链表
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 **/

//执行用时:0 ms ，在所有Java提交中击败了100.00%的用户
//内存消耗:38 MB ，在所有Java提交中击败了14.34%的用户

public class MergeTwoLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode newNode = new ListNode(0);
        ListNode cur = newNode;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        if (l1 == null) {
            cur.next = l2;
        } else {
            cur.next = l1;
        }

        return newNode.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
