package com.dataStructures.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: md
 * @Date: 2020/8/21 19:07
 * 赫夫曼数：节点的带权路径长度最短（wpl）
 * 树的带权路径长度：所有叶子节点的带权路径之和
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,6,1};
        Node huffmanTree = createHuffmanTree(arr);
        preOrder(huffmanTree);
//        String a = "i like like like java do you like a java";
//        System.out.println(Arrays.toString(a.getBytes()));
    }

    public static Node createHuffmanTree(int[] arr){
        //为了操作方便，遍历arr
        //将arr的每个元素构成一个Node
        //将Node放到ArrayList
        List<Node> list = new ArrayList<>();
        for (int value : arr) {
            list.add(new Node(value));
        }
        while (list.size() > 1){
            //排序，从小到大
            Collections.sort(list);
            //System.out.println(list);
            //取出根节点权值最小的两颗二叉树
            Node leftNode = list.get(0);
            Node rightNode = list.get(1);
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //从arrList删除处理过的二叉树
            list.remove(leftNode);
            list.remove(rightNode);
            list.add(parent);
            //System.out.println(list);
        }
        //返回赫夫曼的头结点
        return list.get(0);
    }

    public static void  preOrder(Node node){
        if (node != null){
            node.preOrder();
        }else {
            System.out.println("空树");
        }

    }
}

//为了让node支持排序，让node实现comparable接口
class Node implements Comparable<Node>{
    int value;//节点权值
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}
