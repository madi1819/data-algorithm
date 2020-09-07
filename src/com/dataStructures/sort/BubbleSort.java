package com.dataStructures.sort;

/**
 * @Author: md
 * @Date: 2020/8/11 21:07
 * 冒泡
 */
public class BubbleSort {
    public static void main(String[] args) {
        //int[] arr = {3,9,-1,10,20,5,325,54,215,51,4,485,659,45,65,326,15,55};
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            double random = Math.random() * 80000;
            arr[i] = (int) (random);
        }
        long start = System.currentTimeMillis();
        bubbleSort(arr);
        //System.out.println(Arrays.toString(arr));
        long end = System.currentTimeMillis();
        System.out.println(end - start);


    }

    public static void bubbleSort(int[] arr){
        int temp;
        int len = arr.length - 1;
        int boundary = 0;
        //标识变量
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j+1]){
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    boundary = j;
                }
            }
            len = boundary;
            if (!flag){
                break;
            }else {
                flag = false;
            }
        }
    }
}
