package com.algorithm.prim;

import java.util.Arrays;

/**
 * @Author: md
 * @Date: 2020/9/2 19:49
 * 普利姆算法
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = {'A','B','C','D','E','F','G'};
        int verxs = data.length;
        int[][] weight = {
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000}
        };

        MinTree minTree = new MinTree();
        MGraph graph = new MGraph(verxs);
        minTree.createGraph(graph,verxs,data,weight);
        minTree.showGraph(graph);
        minTree.prim(graph,0);
    }
}

//创建最小生成树-》村庄图
class MinTree {
    /**
     *
     * @param graph  图对象
     * @param verxs  图对应顶点个数
     * @param data   各个顶点值
     * @param weight  邻接矩阵
     */
    public void createGraph(MGraph graph, int verxs, char[] data, int[][] weight){
//        int i,j;
//        for (i = 0; i < verxs; i++) {
//            graph.data[i] = data[i];
//            for (j = 0; j < verxs; j++) {
//                graph.weight[i][j] = weight[i][j];
//            }
//        }
        graph.data = data;
        graph.weight = weight;
    }
    
    //显示图的方法
    public void showGraph(MGraph graph){
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    //编写普利姆算法
    /**
     *
     * @param graph
     * @param v   表示从图的第几个顶点开始生成
     */
    public void prim(MGraph graph, int v){
        //标记顶点是否被访问过,默认元素的值都是0
        int[] visited = new int[graph.verxs];
        //把当前这个节点标记为已访问’
        visited[v] = 1;
        //用h1和h2记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000; //初始化一个大数，在遍历过程会替换
        for (int i = 1; i < graph.verxs; i++) {  //因为有graph.verxs顶点，prim算法结束后，有graph.verxs-1条边

            for (int j = 0; j < graph.verxs; j++) { // 已经访问过的节点
                for (int k = 0; k < graph.verxs; k++) { // 没有访问过的节点
                    if (visited[j] == 1 && visited[k] == 0 && graph.weight[j][k] < minWeight){
                        //寻找已经访问的顶点和未访问的顶点间权值最小的边
                        minWeight = graph.weight[j][k];
                        h1 = j;
                        h2 = k;
                    }
                }
            }
            System.out.println("边<"+graph.data[h1]+","+graph.data[h2]+">权值:"+minWeight);
            visited[h2] = 1;
            //minWeight重置
            minWeight = 10000;
        }
    }
}

class MGraph {
    int verxs; //表示图的节点个数
    char[] data;//存放节点数据
    int[][] weight;//存放边，邻接矩阵

    public MGraph(int verxs) {
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }


}
