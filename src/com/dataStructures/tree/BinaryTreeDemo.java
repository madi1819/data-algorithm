package com.dataStructures.tree;

/**
 * @Author: md
 * @Date: 2020/8/17 21:22
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode heroNode2 = new HeroNode(2, "吴用");
        HeroNode heroNode3 = new HeroNode(3, "林冲");
        HeroNode heroNode4 = new HeroNode(4, "卢俊义");
        HeroNode heroNode5 = new HeroNode(5, "关胜");
        HeroNode heroNode6 = new HeroNode(6, "李逵");
        HeroNode heroNode7 = new HeroNode(7, "鲁智深");
        binaryTree.setRoot(root);
        root.setLeft(heroNode2);
        root.setRight(heroNode3);
        heroNode3.setRight(heroNode4);
        heroNode3.setLeft(heroNode5);
        heroNode5.setLeft(heroNode6);
        heroNode5.setRight(heroNode7);
        System.out.println("前序遍历");
        binaryTree.preOrder();
        System.out.println("中序遍历");
        binaryTree.infixOrder();
        System.out.println("后序遍历");
        binaryTree.postOrder();

        //前序查找
        //前序遍历次数
        System.out.println("前序查找=================》");
        HeroNode pre = binaryTree.preOrderSearch(7);
        if (pre != null){
            System.out.println(pre);
        }else {
            System.out.println("未找到编号英雄");
        }
        System.out.println("中序查找==================》");
        HeroNode infix = binaryTree.infixOrderSearch(7);
        if (infix != null){
            System.out.println(infix);
        }else {
            System.out.println("未找到编号英雄");
        }
        System.out.println("后序查找==================》");
        HeroNode post = binaryTree.postOrderSearch(7);
        if (post != null){
            System.out.println(post);
        }else {
            System.out.println("未找到编号英雄");
        }

        //删除前
        System.out.println("删除前");
        binaryTree.preOrder();
        binaryTree.deleteNode(3);
        System.out.println("删除后");
        binaryTree.preOrder();
    }
}

//定义二叉树
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder(){
        if (this.root != null){
            this.root.preOrder();
        }else {
            System.out.println("二叉树为空");
        }
    }
    //中序遍历
    public void infixOrder(){
        if (this.root != null){
            this.root.infixOrder();
        }else {
            System.out.println("二叉树为空");
        }
    }
    //后序遍历
    public void postOrder(){
        if (this.root != null){
            this.root.postOrder();
        }else {
            System.out.println("二叉树为空");
        }
    }

    //前序查找
    public HeroNode preOrderSearch(int num){
        if (this.root != null){
            return this.root.preOrderSearch(num);
        }else {
            return null;
        }
    }
    //中序遍历
    public HeroNode infixOrderSearch(int num){
        if (this.root != null){
            return this.root.infixOrderSearch(num);
        }else {
            return null;
        }
    }
    //后序遍历
    public HeroNode postOrderSearch(int num){
        if (this.root != null){
            return this.root.postOrderSearch(num);
        }else {
            return null;
        }
    }

    public void deleteNode(int num){
        if (this.root != null){
            if (this.root.getNum() == num){
                root = null;
            }else {
                root.deleteNode(num);
            }
        }else {
            System.out.println("空树，不能删除");
        }
    }
}

class HeroNode {
    private int num;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int num, String name) {
        this.num = num;
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "num=" + num +
                ", name='" + name + '\'' +
                '}';
    }

    //编写前序遍历
    public void preOrder(){
        System.out.println(this);
        //递归向左子树
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }
    //编写中序遍历
    public void infixOrder(){
        if (this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null){
            this.right.infixOrder();
        }
    }
    //编写后序遍历
    public void postOrder(){
        if (this.left != null){
            this.left.postOrder();
        }
        if (this.right != null){
            this.right.postOrder();
        }
        System.out.println(this);
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int num){
        System.out.println("进入前序遍历~~~");
        //比较当前节点是不是
        if (this.num == num){
            return this;
        }
        //判断当前节点的左节点是否空
        //做地柜前序查找，找到返回
        HeroNode resNode = null;
        if (this.left != null){
            resNode = this.left.preOrderSearch(num);
        }
        if (resNode != null){
            return resNode;
        }
        if (this.right != null){
            resNode = this.right.preOrderSearch(num);
        }
        return resNode;
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int num){
        HeroNode resNode = null;
        if (this.left != null){
            resNode = this.left.infixOrderSearch(num);
        }
        if (resNode != null){
            return resNode;
        }
        System.out.println("进入中序遍历~~~");
        if (this.num == num){
            return this;
        }
        if (this.right != null){
            resNode = this.right.infixOrderSearch(num);
        }
        return resNode;
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int num){
        HeroNode resNode = null;
        if (this.left != null){
            resNode = this.left.postOrderSearch(num);
        }
        if (resNode != null){
            return resNode;
        }
        if (this.right != null){
            resNode = this.right.postOrderSearch(num);
        }
        if (resNode != null){
            return resNode;
        }
        System.out.println("进入后序遍历~~~");
        if (this.num == num){
            return this;
        }
        return resNode;
    }

    //递归删除节点
    public void deleteNode(int num){
        if (this.left != null && this.left.num == num){
            this.left = null;
            return;
        }
        if (this.right != null && this.right.num == num){
            this.right = null;
            return;
        }
        if (this.left != null){
            this.left.deleteNode(num);
        }
        if (this.right != null){
            this.right.deleteNode(num);
        }
    }
}
