package com.algorithm.dac;

/**
 * @Author: md
 * @Date: 2020/8/31 20:31
 */
public class Hanoitower {
    public static void main(String[] args) {
        hanoiTower(3,'A','B','C');
    }

    public static void hanoiTower(int num,char a,char b,char c){
        if (num == 1){
            System.out.println("第1个盘从"+a+"->"+c);
        }else {
            //先把最上面的盘A-》B,移动过程使用C
            hanoiTower(num - 1,a,c,b);
            System.out.println("第"+num+"个盘从"+a+"->"+c);
            //把B的移动到C
            hanoiTower(num - 1,b,a,c);
        }
    }
}
