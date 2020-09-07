package com.algorithm.kruskal;

import java.util.Arrays;

/**
 * @Author: md
 * @Date: 2020/9/2 21:07
 * 克鲁斯卡尔算法
 */
public class KruskalCase {

    private int edgeNum;  //边的个数
    private char[] vertexs; //顶点数组
    private int[][] matrix; //邻接矩阵

    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexs = {'A','B','C','D','E','F','G'};
        int[][] matrix = {
                {0,12,INF,INF,INF,16,14},
                {12,0,10,INF,INF,7,INF},
                {INF,10,0,3,5,6,INF},
                {INF,INF,3,0,4,INF,INF},
                {INF,INF,5,4,0,2,8},
                {16,7,6,INF,2,0,9},
                {14,INF,INF,INF,8,9,0}
        };
        KruskalCase kruskalCase = new KruskalCase(vertexs, matrix);
        kruskalCase.print();
        EData[] edges = kruskalCase.getEdges();
        System.out.println(Arrays.toString(edges));
        kruskalCase.sortEdges(edges);
        System.out.println(Arrays.toString(edges));
        kruskalCase.kruskal();
    }

    public KruskalCase(char[] vertexs, int[][] matrix) {
        this.vertexs = vertexs;
        this.matrix = matrix;
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i+1; j < vertexs.length; j++) {
                if (this.matrix[i][j] != INF){
                    edgeNum ++;
                }
            }
        }
        System.out.println(edgeNum);
    }



    public void kruskal(){
        int index = 0;// 表示最后结果数组的索引
        int[] ends = new int[edgeNum]; //用于保存  已有最小生成树  中的每个顶点在最小生成树中的终点
        //创建结果数组，保存最后的最小生成树
        EData[] rets = new EData[edgeNum];

        //获取图中所有的边的集合
        EData[] edges = getEdges();

        //按照边的圈住权值大小排序
        sortEdges(edges);

        //遍历edges数组,将边添加到最小生成树中时，判断准备加入的边是否形成回路
        for (int i = 0; i < edgeNum; i++) {
            //获取第i条边的第一个顶点
            int p1 = getPosition(edges[i].start);
            //获取第i条边的第2个顶点
            int p2 = getPosition(edges[i].end);

            //获取p1顶点在已有的最小生成树中的终点是哪个
            int m = getEnd(ends, p1);
            //获取p2顶点在已有的最小生成树中的终点是哪个
            int n = getEnd(ends, p2);

            //是否构成回路
            if (m != n){
                ends[m] = n;  //设置m在 已有最小生成树 的终点
                rets[index++] = edges[i];
            }
        }
        for (int i = 0; i < index; i++) {
            System.out.println(rets[i]);
        }
    }

    
    //打印邻接矩阵
    public void print(){
        System.out.println("邻接矩阵：\n");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%-12d\t",matrix[i][j]);
            }
            System.out.println();
        }
    }


    //对边进行排序处理
    private void sortEdges(EData[] edges){
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j+1].weight){
                    EData tmp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = tmp;
                }
            }
        }
    }

    /**
     *
     * @param ch   顶点的值
     * @return  返回顶点对应的下标
     */
    private int getPosition(char ch){
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch){
                return i;
            }
        }
        return -1;
    }

    //获取图中的边，放入EData[]数组，后面需要遍历该数组
    //是通过matrix邻接矩阵获取
    private EData[] getEdges(){
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if (matrix[i][j] != INF){
                    edges[index++] = new EData(vertexs[i],vertexs[j],matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 获取下标为i的顶点的终点，用户后面判断两个顶点的终点是否相同
     * @param ends 记录各个顶点对应的终点是哪个，逐步形成
     * @param i 传入顶点的对应的下标
     * @return 返回下标为i的这个顶点对应的终点的下标
     */
    private int getEnd(int[] ends,int i){
        while (ends[i] != 0){
            i = ends[i];
        }
        return i;
    }
}

//创建一个类EData，他的对象实例表示一条边
class EData {
    char start; //边的一个点
    char end; //边的另外一个点
    int weight;//权值

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }

}
