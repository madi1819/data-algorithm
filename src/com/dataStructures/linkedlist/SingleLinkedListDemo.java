package com.dataStructures.linkedlist;

import java.util.Stack;

/**
 * @Author: md
 * @Date: 2020/8/7 12:52
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode heroNode = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode1 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode2 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode3 = new HeroNode(4, "林冲", "豹子头");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addByOrder(heroNode);
        singleLinkedList.addByOrder(heroNode3);
        singleLinkedList.addByOrder(heroNode1);
        singleLinkedList.addByOrder(heroNode2);
        singleLinkedList.list();

        System.out.println("打印");

        reversePrint(singleLinkedList.getHead());


        //singleLinkedList.list();

        reversetList(singleLinkedList.getHead());
        System.out.println("翻转");
        singleLinkedList.list();


        HeroNode heroNode4 = new HeroNode(2, "鲁智深", "花和尚");
        System.out.println("修改后");
        singleLinkedList.update(heroNode4);
        singleLinkedList.list();
//        singleLinkedList.delete(heroNode);
//        singleLinkedList.delete(heroNode3);
        System.out.println("删除后");
        singleLinkedList.list();

        System.out.println(getLength(singleLinkedList.getHead()));
        HeroNode heroNode5 = getHeroNode(singleLinkedList, 0);
        System.out.println(heroNode5);
    }

    //查找单链表的倒数第k个节点
    public static HeroNode getHeroNode(SingleLinkedList singleLinkedList, int index){

        int length = getLength(singleLinkedList.getHead());
        int i = length - index;
        if (i < 0){
            return null;
        }
        HeroNode head = singleLinkedList.getHead();
        HeroNode temp = head;
        int count = 0;
        while (true){
            if (temp.next == null){
                break;
            }
            temp = temp.next;
            count ++;
            if (count == i+1){
                return temp;
            }
        }
        return null;
    }

    //翻转链表
    public static void reversetList(HeroNode head){
        if (head.next == null || head.next.next == null){
            return;
        }
        HeroNode reverseHead = new HeroNode(0,"","");
        HeroNode cur = head.next;
        HeroNode next = null;
       while (cur != null){
          next = cur.next;
          cur.next = reverseHead.next;
          reverseHead.next = cur;
          cur = next;
       }
       head.next = reverseHead.next;
    }

    //翻转递归
    public static HeroNode reservetPrint(HeroNode head){
        if (head.next == null){
            return head;
        }
        HeroNode temp = head.next;
        HeroNode newHead = reservetPrint(head.next);
        temp.next = head;
        head.next = null;
        return newHead;
    }

    //使用栈逆序打印
    public static void reversePrint(HeroNode head){
        if (head.next == null){
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode temp = head.next;
        while (temp != null){
            stack.add(temp);
            temp = temp.next;
        }
        while (stack.size() > 0){
            System.out.println(stack.pop());
        }
    }

    //获取单链表的节点个数
    public static int getLength(HeroNode head){
        if (head.next == null){
            return 0;
        }
        int length = 0;
        HeroNode temp = head.next;
        while (temp != null){
            temp = temp.next;
            length++;
        }
        return length;
    }
}


class SingleLinkedList {
    //初始化头结点
    private HeroNode head = new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }

    //思路：当不考虑编号顺序
    //找到当前链表最后节点
    //将这个节点的next指向新节点
    public void add(HeroNode heroNode){
        //head不能动
        HeroNode temp = head;

        while (true){
            if (temp.next == null){
                break;
            }
            temp = temp.next;
        }
        //退出while，temp指向了链表的最后
        temp.next = heroNode;
    }

    public void addByOrder(HeroNode heroNode){
        //通过辅助变量帮助找到添加的位置
        HeroNode temp = head;
        boolean flag = false;   //标志添加的编号是否存在
        while (true){
            if (temp.next == null){
                break;
            }
            if (temp.next.num > heroNode.num){
                break;
            }else if (temp.next.num == heroNode.num){
                flag = true;//编号存在
                break;
            }
            temp = temp.next;
        }
        if (flag){
            System.out.println("编号存在.........");
        }else {
            heroNode.next = temp.next;
            temp.next = heroNode;

        }
    }

    public void update(HeroNode heroNode){
        if (head.next == null){
            System.out.println("空...........");
            return;
        }
        HeroNode temp = head;
        boolean flag = false;
        while (true){
            if (temp.next == null){
                break;
            }
            if (temp.num == heroNode.num){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.name = heroNode.name;
            temp.nickName = heroNode.nickName;
        }else {
            System.out.println("未找到..............");
        }
    }
    public void delete(HeroNode heroNode){
        if (head.next == null){
            System.out.println("空...........");
            return;
        }
        HeroNode temp = head;
        boolean flag = false;
        while (true){
            if (temp.next == null){
                break;
            }
            if (temp.next.num == heroNode.num){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.next = temp.next.next;
        }else {
            System.out.println("未找到节点");
        }
    }
    //显示链表
    public void list(){
        //判断是否为空
        if (head.next == null){
            System.out.println("空....................");
            return;
        }
        HeroNode temp = head.next;
        while (true){
            if (temp == null){
                break;
            }
            //输出
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class HeroNode {
    public int num;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int num, String name, String nickName) {
        this.num = num;
        this.name = name;
        this.nickName = nickName;
    }
    @Override
    public String toString() {
        return "HeroNode{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
