package com.algorithm.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: md
 * @Date: 2020/9/4 17:31
 * 马踏棋盘
 */
public class HorseChessBoard {

    private static int X; //棋盘列数
    private static int Y; //棋盘行数

    //创建一个数组，标记棋盘的各个位置是否被访问过
    private static boolean visited[];
    //使用一个属性标记是否期盼的所有位置都被访问过了
    private static boolean finished;

    public static void main(String[] args) {
        //测试
        X = 8 ;
        Y = 8 ;
        int row = 1;
        int column = 1;
        //创建棋盘
        int[][] chessBoard = new int[X][Y];
        visited = new boolean[X * Y];
        long start = System.currentTimeMillis();
        traversalChessBoard(chessBoard,row - 1,column - 1,1);
        long end = System.currentTimeMillis();
        System.out.println("共耗时："+(end - start));
        //输出棋盘的最后情况
        for (int[] ints : chessBoard) {
            for (int step : ints) {
                System.out.printf(step+"\t");
            }
            System.out.println();
        }
    }

    /**
     * 完成骑士周游的算法
     * @param chessBoard  棋盘
     * @param row  马儿当前的行 从0开始
     * @param column 马儿当前的列 从0开始
     * @param step 是第几步，起始位置就是第一步
     */
    public static void traversalChessBoard(int[][] chessBoard, int row,int column,int step){
        chessBoard[row][column] = step;
        visited[row * X + column] = true; //标记该位置已访问  一维数组换二维数组
        //获取当前位置可以走的下一个位置的集合
        List<Point> ps = next(new Point(column, row));

        //对ps进行排序，排序规则是对ps的所有的Point对象的下一步的位置数目，进行非递减排序
        sort(ps);
        //遍历ps
        while (!ps.isEmpty()){
            Point p = ps.remove(0);  //取出下一个可以走的位置
            //判断该点是否已经访问过
            if (!visited[p.y * X + p.x]){
                traversalChessBoard(chessBoard,p.y,p.x,step + 1);
            }
        }
        //判断马儿是否完成任务，使用 step 和应该走的部署比较
        //如果没有达到数量，则表示未完成，将整个棋盘置为0
        if (step < X * Y && !finished){
            chessBoard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            finished = true;
        }
    }

    /**
     * 根据当前位置，计算马还能走哪些位置，并放入集合，最多8个位置
     * @param curPoint
     * @return
     */
    public static List<Point> next(Point curPoint) {
        //创建集合
        List<Point> ps = new ArrayList<>();
        //创建Point
        Point p1 = new Point();
        //代表可以走的点位
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }

        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }

        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }

        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        return ps;
    }

    //根据当前这一步所有的下一步的选择位置，进行非递减排序
    public static void sort(List<Point> ps){
        /*ps.sort((o1, o2) -> {
            Integer count1 = next(o1).size();
            Integer count2 = next(o2).size();
            if (count1 < count2){
                return -1;
            }else if (count1 == count2){
                return 0;
            }else {
                return 1;
            }
        });*/
        ps.sort((o1, o2) -> Integer.valueOf(next(o1).size()).compareTo(Integer.valueOf(next(o2).size())));
    }
}
