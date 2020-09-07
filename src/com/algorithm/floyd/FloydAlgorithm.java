package com.algorithm.floyd;

import java.util.Arrays;

/**
 * @Author: md
 * @Date: 2020/9/4 15:11
 * 弗洛伊德算法
 */
public class FloydAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A','B','C','D','E','F','G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, 0, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};

        //创建图对象
        Graph graph = new Graph(vertex.length, matrix, vertex);
        graph.floyd();
        graph.show();
    }
}

class Graph {
    private char[] vertex; //存放定点的数组
    private int[][] dis; //保存从各个顶点出发到其他顶点的总数，最后的结果也是保存在该数组
    private int[][] pre; //保存到达目标定点的前驱顶点

    public Graph(int length, int[][] matrix, char[] vertex) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];
        for (int i = 0; i < pre.length; i++) {
            Arrays.fill(pre[i],i);
        }
    }

    //显示pre和dis
    public void show(){
        char[] vertex = {'A','B','C','D','E','F','G'};
        for (int i = 0; i < dis.length; i++) {
            for (int j = 0; j < pre.length; j++) {
                System.out.printf(vertex[pre[i][j]] + " ");
            }
            System.out.println();
            for (int j = 0; j < dis.length; j++) {
                System.out.printf("("+vertex[i]+"-"+vertex[j]+"的最短路径是"+dis[i][j] + ") ");
            }
            System.out.println();
            System.out.println();
        }
    }

    //完成呢个弗洛伊德算法
    public void floyd(){
        int len = 0; //变量保存距离
        //对中间顶点遍历{'A','B','C','D','E','F','G'};
        for (int k = 0; k < dis.length; k++) {
            //从i顶点开始出发{'A','B','C','D','E','F','G'};
            for (int i = 0; i < dis.length; i++) {
                //终点节点{'A','B','C','D','E','F','G'};
                for (int j = 0; j < dis.length; j++) {
                    len = dis[i][k] + dis[k][j];  //从i出发，经过中间节点k，到达j顶点距离
                    if (len < dis[i][j]){
                        dis[i][j] = len;
                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }
    }
}
