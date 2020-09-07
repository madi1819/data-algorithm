package com.dataStructures.recursion;

/**
 * @Author: md
 * @Date: 2020/8/11 15:36
 */
public class Queue8 {

    int max = 8;

    //定义数组，保存皇后放置位置的结果
    int[] array = new int[max];
    static int count = 0;
    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println("一共："+count);
    }

    //写一个方法，将皇后摆放的位置输出
    private void print(){
        for (int i = 0; i < array.length; i++) {
            System.out.printf(array[i]+" ");
        }
        count++;
        System.out.println();
    }

    //查看当我们放置第n个皇后，就去检测该皇后是否和前面已经摆放的皇后冲突
    private boolean judge(int n){
        for (int i = 0; i < n; i++) {
            //array[i] == array[n] 表示判断第n个皇后是否和前面的n-1个皇后在同一列
            //Math.abs(n-i) == Math.abs(array[n] - array[i]) 表示判断第n个皇后是否和前面的n-1个皇后在同一斜线
            if (array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i])){
                return false;
            }
        }
        return true;
    }

    //编写方法放置第n个皇后
    private void check(int n){
        if (n == max){
            print();
            return;
        }
        for (int i = 0; i < max; i++) {
            //先把当前皇后n，放到该行的第一列
            array[n] = i;
            //判断当放到第n个皇后到第i列，是否冲突
            if (judge(n)){
                check(n+1);
            }
        }
    }
}
