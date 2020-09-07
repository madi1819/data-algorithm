package com.algorithm.dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: md
 * @Date: 2020/9/3 19:31
 * 迪杰斯特拉算法
 */
public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A','B','C','D','E','F','G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};

        //创建Graph
        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();
        graph.dsj(2);
        graph.showDsj();
        graph.showPath(2);

    }
}


class Graph {
    private char[] vertex; //顶点数组
    private int[][] matrix; //邻接矩阵
    private VisitedVertex vv; //已经访问的顶点的集合

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    //显示结果
    public void showDsj(){
        vv.show();
    }

    public void showPath(int index){
        vv.showPath(index);
    }


    //显示图
    public void showGraph(){
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }

    //迪杰斯特拉算法
    /**
     *
     * @param index 表示出发节点对应的下标
     */
    public void dsj(int index){
        vv = new VisitedVertex(vertex.length, index);
        //更新index顶点到周围顶点的距离和前驱顶点
        update(index);
        for (int i = 1; i < vertex.length; i++) {
            index = vv.updateArr(); //选择并返回新的访问顶点
            update(index); //更新index下标顶点到周围顶点的距离和  周围顶点的前驱顶点
        }
    }

    //更新index下标顶点到周围顶点的距离和  周围顶点的前驱顶点
    public void update(int index){
        int len = 0;
        for (int i = 0; i < matrix[index].length; i++) {
            //出发顶点到index顶点的距离+从index顶点到i顶点的距离和（广度算法）
            len = vv.getDis(index) + matrix[index][i];
            //如果i顶点没有被访问过，并且len小于出发顶点到i的顶点的距离，就需要更新
            if (!vv.in(i) && len < vv.getDis(i)){
                vv.updatePre(i,index);  //更新i顶点的前驱为index顶点
                vv.updateDis(i,len); //更新出发定点到i顶点的距离
            }
        }
    }
}

//已访问顶点集合
class VisitedVertex {
    //记录各个顶点是否访问过，1访问，0未访问
    public int[] already_arr;
    //每个下表对应的值为前一个顶点的下标，会动态更新
    public int[] pre_visited;
    //记录出发定点到其他所有顶点的距离
    public int[] dis;

    /**
     *
     * @param length 表示顶点的个数
     * @param index 出发定点
     */
    public VisitedVertex(int length,int index) {
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        //初始化dis数组
        Arrays.fill(dis,65535);
        this.already_arr[index] = 1; //设置出发顶点为1
        this.dis[index] = 0;
    }

    /**
     * 判断index顶点是否被访问过
     * @param index
     * @return 访问返回true
     */
    public boolean in(int index){
        return already_arr[index] == 1;
    }

    /**
     * 更新触发定点到index顶点的距离
     * @param index
     * @param len
     */
    public void updateDis(int index,int len){
        dis[index] = len;
    }

    /**
     * 更新pre顶点的前驱为index的节点
     * @param pre
     * @param index
     */
    public void updatePre(int pre,int index){
        pre_visited[pre] = index;
    }

    /**
     * 返回出发顶点到index顶点的距离
     * @param index
     */
    public int getDis(int index){
        return dis[index];
    }

    //继续选择并返回新的访问顶点，比如G完后，A就是新的访问顶点（不是触发定点）
    public int updateArr(){
        int min = 65535,index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if (already_arr[i] == 0 && dis[i] < min){
                min = dis[i];
                index = i;
            }
        }
        //更新index被访问过
        already_arr[index] = 1;
        return index;
    }

    //显示最后的结果
    //即三个数组输出
    public void show(){
        System.out.println("=======================>");
        for (int i : already_arr) {
            System.out.printf(i + " ");
        }
        System.out.println();
        for (int i : pre_visited) {
            System.out.printf(i + " ");
        }
        System.out.println();
        for (int i : dis) {
            System.out.printf(i + " ");
        }

    }

    public void addPre(int i,int index,char[] vertex,List list){
        if (i == index){
            return;
        }
        int pre = pre_visited[i];
        addPre(pre,index,vertex,list);
        list.add(vertex[pre]);
    }

    //展示路径详情
    public void showPath(int index){
        char[] vertex = {'A','B','C','D','E','F','G'};
        List<List> list1 = new ArrayList();
        for (int i = 0; i < pre_visited.length; i++) {
            List list = new ArrayList();
            int pre = pre_visited[i];
//           while (i1 != index){
//               list.add(i1);
//               i1 = pre_visited[i1];
//           }
//           list.add(i1);
            if (i != index){
                addPre(pre,index,vertex,list);
                list.add(vertex[pre]);
                list.add(vertex[i]);
            }else {
                list.add("");
            }

           list1.add(list);
        }
        for (List list : list1) {
            System.out.println("-------------");
            System.out.println(list);
        }
    }

}