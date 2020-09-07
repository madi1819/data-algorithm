package com.dataStructures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: md
 * @Date: 2020/8/28 21:00
 * 图
 */
public class Graph {

    private List<String> vertexList; //存储顶点集合
    private int[][] edges; //存储对应的邻接矩阵
    private int numOfEdges; //表示边的数目
    //定义数组boolean[]，记录某个节点是否被访问
    private boolean[] isVisited;

    public static void main(String[] args) {
        int n = 5;
        String vertexValue[] = {"A","B","C","D","E"};
        Graph graph = new Graph(n);
        for (String s : vertexValue) {
            graph.insertVertex(s);
        }
        //添加边
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);

        graph.showGraph();

        //graph.dfs();

        graph.bfs();
    }

    //构造器
    public Graph(int n) {
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
        isVisited = new boolean[5];
    }

    //得到第一个邻接节点的下标w
    /**
     *
     * @param index 如果存在返回对应的下标
     * @return
     */
    public int getFirstNeighbor(int index){
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0){
                return i;
            }
        }
        return -1;
    }

    //根据前一个邻接节点的下标来获取下一个邻接节点
    public int getNextNeightor(int v1,int v2){
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0){
                return i;
            }
        }
        return -1;
    }

    //深度优先遍历算法 Depth first Search
    private void dfs(boolean[] isVisited,int i){
        //首先访问该节点，输出
        System.out.println(getValueByIndex(i)+"->");
        //将该节点设置为已经访问
        isVisited[i] = true;
        //查找节点i的第一个邻接节点w
        int w = getFirstNeighbor(i);
        while (w != -1){
            if (!isVisited[w]){
                dfs(isVisited,w);
            }
            //如果w节点被访问过，就应该查询邻接点的下一个邻接节点
            w = getNextNeightor(i,w);
        }
    }

    //对dfs进行重载，遍历所有节点，并进行dfs
    public void dfs(){
        //遍历所有的节点进行dfs
        for (int i = 0; i < getNumofVertex(); i++) {
            if (!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }

    //对一个节点进行广度优先遍历的方法 Breadth-First-Search
    private void bfs(boolean[] isVisited,int i){
        int u; //表示队列的头结点对应下标
        int w; //表示邻接节点w
        //队列，节点访问顺序
        LinkedList queue = new LinkedList<>();
        //访问节点，输出节点信息
        System.out.println(getValueByIndex(i)+"->");
        //标志已访问
        isVisited[i] = true;
        queue.addLast(i);
        while (!queue.isEmpty()){
            //取出队列的头节点下标
            u = (int) queue.removeFirst();
            //得到第一个邻接点的下标
            w = getFirstNeighbor(u);
            if (w != -1){
                //是否访问过
                if (!isVisited[w]){
                    System.out.println(getValueByIndex(w)+"->");
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                //以u为前驱点找w后面的下一个邻接点
                w = getNextNeightor(u,w); //体现出广度优先
            }
        }
    }

    //遍历所有的节点都进行广度优先搜索
    public void bfs(){
        for (int i = 0; i < getNumofVertex(); i++) {
            if (!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }

    //图中常用的方法
    //返回节点的个数
    public int getNumofVertex(){
        return vertexList.size();
    }

    //得到边的数目
    public int getNumOfEdges(){
        return numOfEdges;
    }
    //返回节点i对应的数据
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }

    //返回v1和v2的权值
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }

    //显示图对应的矩阵
    public void showGraph(){
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }
    //插入顶点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    //添加边
    /**
     *
     * @param v1 表示点的下标，即第几个顶点
     * @param v2 表示第二个顶点的下标
     * @param weight 表示权值
     */
    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
