package com.dataStructures.linkedlist;

/**
 * @Author: md
 * @Date: 2020/8/8 20:08
 */
public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();
        circleSingleLinkedList.countBoy(3,2,5);
    }
}


//环形链表
class CircleSingleLinkedList {
    //创建first节点，当前没有编号
    private Boy first = null;
    
    //添加小孩节点，构建环形链表
    public void addBoy(int num){
        if (num < 2){
            System.out.println("num值不正确");
            return;
        }
        Boy curBoy = null;
        for (int i = 1; i <= num; i++) {
            //根据编号创建小孩节点
           Boy boy = new Boy(i);
           if (i == 1){
               first = boy;
               first.setNext(first);
               curBoy = first;
           }else {
               curBoy.setNext(boy);
               boy.setNext(first);
               curBoy = boy;
           }
        }
    }

    //遍历
    public void showBoy(){
        if (first == null){
            System.out.println("链表为空");
        }
        Boy curBoy = first;
        while (true){
            System.out.println("小孩的编号："+curBoy.getNum());
            if (curBoy.getNext() == first){
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    //根据用户的输入计算出出圈的顺序

    /**
     *
     * @param startNum 从第几个小孩开始数
     * @param countNum 数几下
     * @param num  最初有多少个小孩
     */
    public void countBoy(int startNum,int countNum,int num){
        if (first == null || startNum < 1 || startNum > num){
            System.out.println("参数输入有误");
        }

        //创建辅助指针，帮助完成小孩出圈
        Boy helper = first;
        //创建辅助指针，事先指向环形最后一个
        while (true){
            if (helper.getNext() == first){
                break;
            }
            helper = helper.getNext();

        }
        for (int i = 0; i < startNum - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //出圈
        while (true){
            if (helper == first){
                break;
            }
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.println("要出圈的小孩："+first.getNum());
            first = first.getNext();
            helper.setNext(first);

        }
        System.out.println("最后的小孩："+first.getNum());
    }
    
}


class Boy {
    private int num;
    private Boy next;

    public Boy(int num) {
        this.num = num;
    }
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public Boy getNext() {
        return next;
    }
    public void setNext(Boy next) {
        this.next = next;
    }
}
