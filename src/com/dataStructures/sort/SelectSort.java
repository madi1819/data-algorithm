package com.dataStructures.sort;

/**
 * @Author: md
 * @Date: 2020/8/12 11:59
 * 选择
 */
public class SelectSort {

    public static void main(String[] args) {
        //int[] arr = {3,12,11,1};
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            double random = Math.random() * 80000;
            arr[i] = (int) (random);
        }
        long start = System.currentTimeMillis();
        selectSort(arr);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void selectSort(int[] arr){

        //找最小的和前面交换

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length ; j++) {
                if (min > arr[j]){
                    min = arr[j];
                    minIndex = j;
                }
                if (minIndex != i){
                    arr[minIndex] = arr[i];
                    arr[i] = min;
                }
            }
        }
    }
}
