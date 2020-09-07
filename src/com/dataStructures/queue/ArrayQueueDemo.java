package com.dataStructures.queue;

import java.util.Scanner;

/**
 * @Author: md
 * @Date: 2020/8/6 20:00
 */
public class ArrayQueueDemo {

    public static void main(String[] args) {
        //创建队列
        ArrayQueue arrayQueue = new ArrayQueue(3);
        Scanner scanner = new Scanner(System.in);
        char key = ' ';
        boolean loop = true;
        while (loop){
            System.out.println("s(show)");
            System.out.println("e(exit)");
            System.out.println("a(add)");
            System.out.println("f(get)");
            System.out.println("q(head)");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'a':
                    System.out.println("输入");
                    int i = scanner.nextInt();
                    arrayQueue.addQueue(i);
                    break;
                case 'f':
                    try {
                        int queue = arrayQueue.getQueue();
                        System.out.println("取出："+queue);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case 'q':
                    try {
                        int queue = arrayQueue.headQueue();
                        System.out.println("取出："+queue);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    arrayQueue.showQueue();
                    break;
            }
        }
        System.out.println("退出");

    }

}

class ArrayQueue{
    private int maxSize;
    private int head;
    private int tail;
    private int[] arr;

    //创建队列的构造器
    public ArrayQueue(int maxSize){
        this.maxSize = maxSize;
        this.head = -1;
        this.tail = -1;
        this.arr = new int[maxSize];
    }

    public boolean isFull(){
        return tail == maxSize -1;
    }
    public boolean isEmpty(){
        return head == tail;
    }

    public void addQueue(int n){
        if (isFull()){
            System.out.println("队列满");
            return;
        }
        tail ++;
        arr[tail] = n;
    }

    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列空");
        }
        head ++;
        return arr[head];
    }

    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列空");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d",i,arr[i]);
        }
    }

    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列空");
        }
        return arr[head+1];
    }
}

