package com.algorithm.dynamic;

/**
 * @Author: md
 * @Date: 2020/9/1 20:12
 * 动态规划算法背包问题
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1,1,1}; //物品重量
        int[] val = {3000,3000,3000};
        int m = 4; //背包容量
        int n = val.length; // 物品个数

        //为了记录放入商品的情况，我们定一个二维数组
        int[][] path = new int[n+1][m+1];
        //创建二维数组
        //v[i][j]表示在前i个物品中能够装入容量为j的背包最大价值
        int[][] v = new int[n+1][m+1];

        //初始化第一行第一列,在本程序可不处理，默认0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }


        for (int i = 1; i < v.length; i++) {  //不处理第一行
            for (int j = 1; j < v[0].length; j++) { //不处理第一列
                if (w[i - 1] > j){
                    v[i][j] = v[i - 1][j];
                }else {
                    //v[i][j] = Math.max(v[i - 1][j],val[i - 1] + v[i - 1][j - w[i - 1]]);
                    //为了记录商品存放背包的情况，我们不能简单的使用上述的公式，需要if-else体现
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]){
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        path[i][j] = 1;
                    }else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }


        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.printf(v[i][j]+" ");
            }
            System.out.println();
        }
        int i = path.length - 1;
        int j = path[0].length - 1;
        while (i > 0 && j > 0){
            if (path[i][j] == 1){
                System.out.printf("第%d个商品放入背包\n",i);
                j -= w[i - 1];
            }
            i --;
        }
    }
}
