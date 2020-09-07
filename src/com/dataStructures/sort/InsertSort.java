package com.dataStructures.sort;

/**
 * @Author: md
 * @Date: 2020/8/12 13:33
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            double random = Math.random() * 8000000;
            arr[i] = (int) (random);
        }
        long start = System.currentTimeMillis();
        insertSort(arr);
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }

    public static void insertSort(int[] arr){

        for (int i = 1; i < arr.length; i++) {
            //定义待插入的数
            int insertValue = arr[i];
            int insertIndex = i - 1;
            //保证不越界
            while (insertIndex >= 0 && insertValue < arr[insertIndex]){
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            if (insertIndex + 1 != i){
                arr[insertIndex + 1] = insertValue;
            }
        }
        //System.out.println(Arrays.toString(arr));

    }
}
