package com.dataStructures.linkedlist;

/**
 * @Author: md
 * @Date: 2020/8/8 12:03
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        HeroNode2 heroNode = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 heroNode1 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 heroNode2 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 heroNode3 = new HeroNode2(4, "林冲", "豹子头");
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.addByOrder(heroNode);
        doubleLinkedList.addByOrder(heroNode3);
        doubleLinkedList.addByOrder(heroNode1);
        doubleLinkedList.addByOrder(heroNode2);
        doubleLinkedList.list();
        HeroNode2 heroNode4 = new HeroNode2(2, "鲁智深", "花和尚");
        System.out.println("修改后");
        doubleLinkedList.update(heroNode4);
        doubleLinkedList.list();

        doubleLinkedList.delete(3);
        System.out.println("删除");
        doubleLinkedList.list();
    }

}

class DoubleLinkedList {
    private HeroNode2 head = new HeroNode2(0,"","");

    public HeroNode2 getHead() {
        return head;
    }

    public void list(){
        //判断是否为空
        if (head.next == null){
            System.out.println("空....................");
            return;
        }
        HeroNode2 temp = head.next;
        while (true){
            if (temp == null){
                break;
            }
            //输出
            System.out.println(temp);
            temp = temp.next;
        }
    }

    public void add(HeroNode2 heroNode){
        //head不能动
        HeroNode2 temp = head;

        while (true){
            if (temp.next == null){
                break;
            }
            temp = temp.next;
        }
        //退出while，temp指向了链表的最后
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    public void addByOrder(HeroNode2 heroNode){
        //通过辅助变量帮助找到添加的位置
        HeroNode2 temp = head;
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
            if (temp.next != null){
                temp.next.pre = heroNode;
            }
            temp.next = heroNode;
            heroNode.pre = temp;

        }
    }


    public void update(HeroNode2 heroNode){
        if (head.next == null){
            System.out.println("空...........");
            return;
        }
        HeroNode2 temp = head;
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

    public void delete(int num){
        if (head.next == null){
            System.out.println("空...........");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true){
            if (temp == null){
                break;
            }
            if (temp.num == num){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.pre.next = temp.next;
            if (temp.next != null){
                temp.next.pre = temp.pre;
            }
        }else {
            System.out.println("未找到节点");
        }
    }
}

class HeroNode2 {
    public int num;
    public String name;
    public String nickName;
    public HeroNode2 pre;
    public HeroNode2 next;

    public HeroNode2(int num, String name, String nickName) {
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