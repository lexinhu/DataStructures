package com.xn2001.linkedlist;

import java.util.Stack;

/**
 * @author 乐心湖
 * @date 2020/8/13 23:52
 **/
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //第一种添加方法的测试
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero4);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
        //第二种添加方法的测试
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        //显示
        singleLinkedList.list();
        //测试修改节点的代码
        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
        singleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表情况~~");
        singleLinkedList.list();
        //删除一个节点
//        singleLinkedList.del(1);
//        singleLinkedList.del(4);
//        System.out.println("删除后的链表情况~~");
//        singleLinkedList.list();
        System.out.println("链表的有效个数");
        System.out.println(getLength(singleLinkedList.getHead()));
        reverseListOne(singleLinkedList.getHead());
//        reverseListTwo(singleLinkedList.getHead());
        System.out.println("反转后的链表");
        singleLinkedList.list();
        System.out.println("倒数第二个---");
        System.out.println(findLastIndexNode(singleLinkedList.getHead(), 2));
        System.out.println("从尾到头打印单链表");
        reversePrint(singleLinkedList.getHead());

    }

    /**
     * 求单链表中有效节点个数
     *
     * @param head 单链表的头节点
     * @return 返回有效节点的个数
     */
    public static int getLength(HeroNode head) {
        int length = 0;
        HeroNode temp = head.next;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    /**
     * 单链表的反转
     * 就地反转法
     *
     * @param head
     */
    public static void reverseListOne(HeroNode head) {
        // 声明当前节点，前继节点和后继节点
        HeroNode prev = head.next;
        HeroNode pCur = prev.next;
        while (pCur != null) {
            //将prev后移
            prev.next = pCur.next;
            //将pCur的next改成原链表的第一个
            pCur.next = head.next;
            //将pCur移到最前
            head.next = pCur;
            //让pCur回归到修改后prev的后面，方便下一个循环。
            pCur = prev.next;
        }
    }

    /**
     * 单链表的反转
     * 头节点插入法
     *
     * @param head
     */
    public static void reverseListTwo(HeroNode head) {
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        //定义一个辅助的指针(变量)，帮助我们遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next;
        HeroNode reverseHead = new HeroNode(0, "", "");
        //遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表 reverseHead 的最前端
        while (cur != null) {
            next = cur.next;
            cur.next = reverseHead.next; ////将 cur 的next改成原链表的第一个
            reverseHead.next = cur; //将 cur 连接到新的链表上
            cur = next; //让 cur 后移
        }
        //将 head.next 指向 reverseHead.next , 实现单链表的反转
        head.next = reverseHead.next;
    }

    /**
     * 查找单链表中的倒数第 k 个结点
     *
     * @param head
     * @param index 倒数第 index 个节点
     * @return
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        int length = getLength(head);
        if (index > length || length <= 0) {
            return null;
        }
        HeroNode temp = head.next;
        for (int i = 0; i < length - index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    //从尾到头打印单链表
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

}

class SingleLinkedList {

    //先初始化一个头节点, 头节点不要动, 不存放任何具体数据
    private final HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    /**
     * 添加节点到单向链表
     * 思路，当不考虑编号顺序时
     * 1. 找到当前链表的最后节点
     * 2. 将最后这个节点的 next 指向新的节点
     */
    public void add(HeroNode heroNode) {
        //因为 head 节点不能动，永远在第一位，因此我们需要一个辅助遍历 temp
        HeroNode temp = head;
        //遍历链表，找到最后一个
        while (temp.next != null) {
            temp = temp.next;
        }
        //当退出 while 循环时，temp 就指向了链表的最后
        //将最后这个节点的 next 指向 新的节点
        temp.next = heroNode;
    }

    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
    //如果有这个排名，则添加失败，并给出提示
    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = head;
        boolean flag = false; // flag标志添加的编号是否存在
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == heroNode.no) {
                flag = true; //说明编号存在
                break;
            }
            if (temp.next.no > heroNode.no) {
                break;
            }
            temp = temp.next; //后移，遍历当前链表
        }
        if (flag) {
            System.out.printf("准备插入的英雄的编号 %d 已经存在了, 不能加入\n", heroNode.no);
        } else {
            //插入到链表中, temp的后面
            //先把原来后面的数给heroNode.next
            heroNode.next = temp.next;
            //然后换掉temp.net
            temp.next = heroNode;
        }
    }

    //显示链表[遍历] 、
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //修改节点的信息, 根据 no 编号来修改，即 no 编号不能改.
    //根据 newHeroNode 的 no 来修改即可
    public void update(HeroNode newHeroNode) {
        //判断是否空
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        //找到需要修改的节点, 根据 no 编号
        //定义一个辅助变量
        HeroNode temp = head;
        //表示是否找到该节点
        boolean flag = false;
        while (true) {
            //已经遍历完链表
            //注意这里面向的是temp，前面的添加英雄面向的是temp的下一个
            if (temp == null) {
                break;
            }
            if (temp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据 flag 判断是否找到要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            //没有找到
            System.out.printf("没有找到 编号 %d 的节点，不能修改\n", newHeroNode.no);
        }
    }

    //删除节点
    //1. head 不能动，因此我们需要一个 temp 辅助节点找到待删除节点的前一个节点
    //2. 说明我们在比较时，是 temp.next.no 和 需要删除的节点的 no 比较
    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false; // 标志是否找到待删除节点的
        while (temp.next != null) {
            //已经遍历完链表
            //注意这里面向的是temp.next，我们要把next改成next.next，就可以把next去掉了
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //判断 flag
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }
}

class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next; //指向下一个节点

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }
}