package com.dataStructures.hashtable;

import java.util.Scanner;

/**
 * @Author: md
 * @Date: 2020/8/17 18:36
 */
public class HashTabDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("add:添加");
            System.out.println("list:查询");
            System.out.println("find:查找");
            System.out.println("exit:退出");
            key = scanner.next();
            switch (key){
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    Emp emp = new Emp(id,name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("输入id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
            }
        }
    }
}

//创建HashTable 管理多条链表
class HashTab{
    private EmpLinkedList[] empLinkedListArray;
    private int size;

    public HashTab(int size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];
        //不要忘记分别初始化每个链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    public void add(Emp emp){
        //根据员工的id得到该员工应该加到哪条链表
        int empListLinkedNum = hashFun(emp.id);
        empLinkedListArray[empListLinkedNum].add(emp);
    }

    public void list(){
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i+1);
        }
    }

    public void findEmpById(int id){
        int empListLinkedNum = hashFun(id);
        Emp empById = empLinkedListArray[empListLinkedNum].findEmpById(id);
        if (empById != null){
            System.out.println("第"+(empListLinkedNum + 1)+"条链表找到，雇员信息：id="+empById.id +",name="+ empById.name);
        }else {
            System.out.println("未找到");
        }
    }

    //编写散列函数,使用取磨法
    public int hashFun(int id){
        return id % size;
    }
}

class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class EmpLinkedList{
    //头指针指向第一个Emp，因此我们链表的head是直接指向第一个emp
    private Emp head; //默认null

    //添加雇员
    //id自增，从小到大
    public void add(Emp emp){
        //如果添加第一个
        if (head == null){
            head = emp;
            return;
        }
        //如果不是第一个，使用辅助指针
        Emp curEmp = head;
        while (true){
            if (curEmp.next == null){
                break;
            }
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }


    //遍历
    public void list(int num){
        if (head == null){
            System.out.println("当前链表"+num+"为空");
            return;
        }
        System.out.println("当前链表"+num+"信息为：");
        Emp curEmp = head;
        while (true){
            System.out.println("id="+curEmp.id+",name="+curEmp.name);
            if (curEmp.next == null){
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    //根据id查到雇员
    public Emp findEmpById(int id){
        //判断链表是否为空
        if (head == null){
            System.out.println("链表为空");
            return null;
        }
        Emp curEmp = head;
        while (true){
            if (curEmp.id == id){
                break;
            }
            if (curEmp.next == null){
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }
}
