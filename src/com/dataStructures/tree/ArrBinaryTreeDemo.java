package com.dataStructures.tree;

/**
 * @Author: md
 * @Date: 2020/8/18 19:49
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder();
    }
}

//编写ArrBinaryTree，实现顺序存储二叉树遍历
class ArrBinaryTree {
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder(){
        this.preOrder(0);
    }
    //编写存储二叉树前序遍历
    public void preOrder(int index){
        //如果数组为空，或者arr.length=0
        if (arr == null || arr.length == 0){
            System.out.println("数组为空，不能遍历");
            return;
        }
        System.out.println(arr[index]);
        //向左递归遍历
        if ((index * 2 + 1) < arr.length){
            preOrder(2 * index + 1);
        }
        if ((index * 2 + 2) < arr.length){
            preOrder(2 * index + 2);
        }
    }
}
