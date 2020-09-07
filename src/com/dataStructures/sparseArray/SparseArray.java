package com.dataStructures.sparseArray;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: md
 * @Date: 2020/8/6 12:46
 */
public class SparseArray {
    public static void main(String[] args) throws IOException {
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[5][6] = 2;
        for (int[] ints : chessArr1) {
            for (int anInt : ints) {
                System.out.printf("%d\t",anInt);
            }
            System.out.println();
        }

        //转稀疏数组
        int sum = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0){
                    sum++;
                }
            }
        }
        int sparseArr[][] = new int[sum+1][3];
        sparseArr[0][0] = chessArr1.length;
        sparseArr[0][1] = chessArr1[0].length;
        sparseArr[0][2] = sum;

        //遍历二维数组，将非0存入稀疏数组
        int count = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        FileWriter fileWriter = new FileWriter(new File("G:\\map.txt"));

        for (int i = 0; i < sparseArr.length; i++) {
            for (int j = 0; j < 2; j++) {
                fileWriter.write(sparseArr[i][j]+",");
            }
            fileWriter.write(sparseArr[i][2]+"");
            fileWriter.write("\r\n");
        }
        fileWriter.flush();

        int[][] read = read();

        System.out.println("文件读取");
        for (int[] ints : read) {
            for (int anInt : ints) {
                System.out.printf("%d\t",anInt);
            }
            System.out.println();
        }


//        System.out.println();
//        for (int[] ints : sparseArr) {
//            for (int anInt : ints) {
//                System.out.printf("%d\t",anInt);
//            }
//            System.out.println();
//        }

        //读取第一行
        int chessArr2[][] = new int[read[0][0]][read[0][1]];

        for (int i = 1; i < read.length; i++) {
            for (int j = 0; j < read[i].length; j++) {
                chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
            }
        }

        for (int[] ints : chessArr2) {
            for (int anInt : ints) {
                System.out.printf("%d\t",anInt);
            }
            System.out.println();
        }
    }


    public static int[][] read() throws IOException {
        FileReader fileReader = new FileReader(new File("G:\\map.txt"));

        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String readLine;
        int count = 0;
        List<String> list = new ArrayList();
        while ((readLine = bufferedReader.readLine()) != null){
            list.add(readLine);
        }
        int[][] sparseArr = new int[list.size()][3];
        for (String s : list) {
            String[] split = s.split(",");
            sparseArr[count][0] = Integer.valueOf(split[0]);
            sparseArr[count][1] = Integer.valueOf(split[1]);
            sparseArr[count][2] = Integer.valueOf(split[2]);
            count++;
        }
        return sparseArr;
    }
}
