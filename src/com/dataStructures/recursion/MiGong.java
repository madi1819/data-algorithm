package com.dataStructures.recursion;

/**
 * @Author: md
 * @Date: 2020/8/11 13:26
 */
public class MiGong {
    public static void main(String[] args) {
        //创建二维数组模拟迷宫
        int[][] map = new int[8][7];
        //使用1表示墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        //map[1][2] = 1;
        //map[2][2] = 1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        setWay2(map,3,3);

        System.out.println("小球走完后");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }

    //递归
    /**
     *
     * @param map 地图
     * @param i 从哪个位置找
     * @param j
     * @return 找到返回true
     * 小球到[6,5]找到
     * 当地图为0表示该点还没走过，2表示通路可以走，3表示该点走过，但是走不通
     * 定义一个策略，下-右-上-左
     */
    public static boolean setWay(int[][] map,int i ,int j){
        if (map[6][5] == 2){
            return true;
        }else {
            if (map[i][j] == 0){
                //按策略走
                map[i][j] = 2;
                if (setWay(map,i+1,j)){
                    return true;
                }else if (setWay(map,i,j+1)){
                    return true;
                }else if (setWay(map,i-1,j)){
                    return true;
                }else if (setWay(map,i,j-1)){
                    return true;
                }else {
                    map[i][j] = 3;
                    return false;
                }
            }else { //如果不等于0；可能是1,2,3
                return false;
            }
        }
    }

    /**
     *
     * @param map 地图
     * @param i 从哪个位置找
     * @param j
     * @return 找到返回true
     * 小球到[6,5]找到
     * 当地图为0表示该点还没走过，2表示通路可以走，3表示该点走过，但是走不通
     * 定义一个策略，上-右-下-左
     */
    public static boolean setWay2(int[][] map,int i ,int j){
        if (map[6][5] == 2){
            return true;
        }else {
            if (map[i][j] == 0){
                //按策略走
                map[i][j] = 2;
                if (setWay2(map,i-1,j)){
                    return true;
                }else if (setWay2(map,i,j+1)){
                    return true;
                }else if (setWay2(map,i+1,j)){
                    return true;
                }else if (setWay2(map,i,j-1)){
                    return true;
                }else {
                    map[i][j] = 3;
                    return false;
                }
            }else { //如果不等于0；可能是1,2,3
                return false;
            }
        }
    }
}
