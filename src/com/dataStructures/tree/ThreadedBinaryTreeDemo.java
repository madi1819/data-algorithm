package com.dataStructures.tree;

/**
 * @Author: md
 * @Date: 2020/8/18 20:48
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        ThreadedHeroNode root = new ThreadedHeroNode(1, "tom");
        ThreadedHeroNode node1 = new ThreadedHeroNode(3, "jack");
        ThreadedHeroNode node2 = new ThreadedHeroNode(6, "smith");
        ThreadedHeroNode node3 = new ThreadedHeroNode(8, "mary");
        ThreadedHeroNode node4 = new ThreadedHeroNode(10, "king");
        ThreadedHeroNode node5 = new ThreadedHeroNode(14, "dim");

        root.setLeft(node1);
        root.setRight(node2);
        node1.setLeft(node3);
        node1.setRight(node4);
        node2.setLeft(node5);

        //测试线索化
      /*  ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();
        //测试
        ThreadedHeroNode left = node2.getLeft();
        System.out.println(left);
        ThreadedHeroNode right = node2.getRight();
        System.out.println(right);
        System.out.println("使用线索化遍历中序");
        threadedBinaryTree.threadedList();*/
        /*ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedPostNodes(root);

        ThreadedHeroNode left = node2.getLeft();
        System.out.println(left);
        ThreadedHeroNode right = node2.getRight();
        System.out.println(right);
        System.out.println("使用线索化遍历前序");
        threadedBinaryTree.threadedPreList();*/
        //如果是后序，需要创建二叉树的时候，将parent进行保存。这个是用于后续二叉树的遍历的

        node1.setParent(root);
        node2.setParent(root);
        node3.setParent(node1);
        node4.setParent(node1);
        node5.setParent(node2);

        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedPostNodes(root);

        ThreadedHeroNode left = node2.getLeft();
        System.out.println(left);
        ThreadedHeroNode right = node2.getRight();
        System.out.println(right);
        System.out.println("使用线索化遍历前序");
        threadedBinaryTree.threadedPostList();
    }
}


class ThreadedBinaryTree {
    private ThreadedHeroNode root;
    //为了实现线索化，需要创建   要给指向当前节点的前驱节点的指针
    //在递归进行线索化时，pre 总是要保留前一个节点
    private ThreadedHeroNode pre = null;
    public void setRoot(ThreadedHeroNode root) {
        this.root = root;
    }

    public void threadedNodes(){
        this.threadedNodes(root);
    }

    //遍历线索化二叉树（中序）
    public void threadedList(){
        //定义一个变量，存储当前遍历的节点，从root开始
        ThreadedHeroNode node = root;
        while (node != null){
            //循环找到leftType = 1 的节点
            //后面随着遍历而变化，当leftType=1时，说明该节点是按照线索化处理后的有效节点
            while (node.getLeftType() == 0){
                node = node.getLeft();
            }
            //打印当前节点
            System.out.println(node);
            //如果当前节点的右指针指向的是后稷街店，就一直输出
            while (node.getRightType() == 1){
                node = node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的节点
            node = node.getRight();
        }
    }

    //编写对二叉树进行中序线索化的方法
    /**
     *
     * @param node 当前需要线索化的节点
     */
    public void threadedNodes(ThreadedHeroNode node){
        if (node == null){
            return;
        }
        //先线索化左子树
        threadedNodes(node.getLeft());
        //线索化当前节点

        //处理当前节点的前驱节点
        if (node.getLeft() == null){
            //让当前节点的左指针指向前驱结点
            node.setLeft(pre);
            node.setLeftType(1);
        }
        //处理后继节点
        if (pre != null && pre.getLeftType()==1){
            //让前驱节点的右子针指向当前节点
            pre.setRight(node);
            pre.setRightType(1);
        }
        //!!!!!每处理一个节点，就让当前节点是下一个节点的前驱结点
        pre = node;
        //线索化右子树
        threadedNodes(node.getRight());

    }

    //编写前序线索化遍历
    public void threadedPreList(){
        ThreadedHeroNode node = root;
        while (node != null){
            while (node.getLeftType() == 0){
                System.out.println(node);
                node = node.getLeft();
            }
            System.out.println(node);
            node = node.getRight();
        }

    }

    //编写对二叉树进行前序线索化的方法
    /**
     *
     * @param node 当前需要线索化的节点
     */
    public void threadedPreNodes(ThreadedHeroNode node){
        if (node == null){
            return;
        }
        if (node.getLeft() == null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getLeftType()==1){
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;

        if (node.getLeftType() != 1){
            threadedPreNodes(node.getLeft());
        }
        if (node.getRightType() != 1){
            threadedPreNodes(node.getRight());
        }

    }

    //编写后序线索化遍历
    //后序有点复杂，需要建立二叉树的时候，将节点的parent进行赋值，否则不能遍历成功
    public void threadedPostList(){
        ThreadedHeroNode node = root;
        while (node != null && node.getLeftType() == 0){
            node = node.getLeft();
        }
        while (node != null){
            //右节点是线索
            if (node.getRightType() == 1){
                System.out.println(node);
                pre = node;
                node = node.getRight();
            }else {
                if (node.getRight() == pre){
                    System.out.println(node);
                    if (node == root){
                        return;
                    }
                    pre = node;
                    node = node.getParent();
                }else {
                    node = node.getRight();
                    while (node != null && node.getLeftType() == 0){
                        node = node.getLeft();
                    }
                }
            }
        }

    }

    //编写对二叉树进行后序线索化的方法
    /**
     *
     * @param node 当前需要线索化的节点
     */
    public void threadedPostNodes(ThreadedHeroNode node){
        if (node == null){
            return;
        }
        threadedPostNodes(node.getLeft());
        threadedPostNodes(node.getRight());
        if (node.getLeft() == null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
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
    public ThreadedHeroNode preOrderSearch(int num){
        if (this.root != null){
            return this.root.preOrderSearch(num);
        }else {
            return null;
        }
    }
    //中序遍历
    public ThreadedHeroNode infixOrderSearch(int num){
        if (this.root != null){
            return this.root.infixOrderSearch(num);
        }else {
            return null;
        }
    }
    //后序遍历
    public ThreadedHeroNode postOrderSearch(int num){
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

class ThreadedHeroNode {
    private int num;
    private String name;
    private ThreadedHeroNode left;
    private ThreadedHeroNode right;
    //后序线索化使用
    private ThreadedHeroNode parent;

    //leftType==0，表示指向左子树，1表示指向前驱结点
    private int leftType;
    //rightType==0，表示指向右子树，1表示指向后继结点
    private int rightType;

    public ThreadedHeroNode(int num, String name) {
        this.num = num;
        this.name = name;
    }

    public ThreadedHeroNode getParent() {
        return parent;
    }

    public void setParent(ThreadedHeroNode parent) {
        this.parent = parent;
    }

    public int getLeftType() {
        return leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
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

    public ThreadedHeroNode getLeft() {
        return left;
    }

    public void setLeft(ThreadedHeroNode left) {
        this.left = left;
    }

    public ThreadedHeroNode getRight() {
        return right;
    }

    public void setRight(ThreadedHeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "ThreadedHeroNode{" +
                "num=" + num +
                ", name='" + name + '\'' +
                '}';
    }

    //编写前序遍历
    public void preOrder() {
        System.out.println(this);
        //递归向左子树
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //编写中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //编写后序遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    //前序遍历查找
    public ThreadedHeroNode preOrderSearch(int num) {
        System.out.println("进入前序遍历~~~");
        //比较当前节点是不是
        if (this.num == num) {
            return this;
        }
        //判断当前节点的左节点是否空
        //做地柜前序查找，找到返回
        ThreadedHeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(num);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.preOrderSearch(num);
        }
        return resNode;
    }

    //中序遍历查找
    public ThreadedHeroNode infixOrderSearch(int num) {
        ThreadedHeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(num);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入中序遍历~~~");
        if (this.num == num) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(num);
        }
        return resNode;
    }

    //后序遍历查找
    public ThreadedHeroNode postOrderSearch(int num) {
        ThreadedHeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(num);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.postOrderSearch(num);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入后序遍历~~~");
        if (this.num == num) {
            return this;
        }
        return resNode;
    }

    //递归删除节点
    public void deleteNode(int num) {
        if (this.left != null && this.left.num == num) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.num == num) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.deleteNode(num);
        }
        if (this.right != null) {
            this.right.deleteNode(num);
        }
    }
}


