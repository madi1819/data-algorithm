package com.dataStructures.sort;

/**
 * @Author: md
 * @Date: 2020/8/12 17:34
 */
public class ShellSort {
    public static void main(String[] args) {

        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            double random = Math.random() * 8000000;
            arr[i] = (int) (random);
        }
        long start = System.currentTimeMillis();
        shellSort2(arr);
        long end = System.currentTimeMillis();
        System.out.println(end - start);


    }

    //交换法
    public static void shellSort(int[] arr){

        //第一轮
        //第一轮排序，是将10个数据分成5组
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中所有的元素
                for (int j = i - gap; j >= 0 ; j -= gap) {
                    //如果当前元素大于加上步长后的那个元素，说明需要交换
                    if (arr[j] > arr[j+gap]){
                        int temp = arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap] = temp;
                    }
                }
            }
        }

        //System.out.println(Arrays.toString(arr));

       /* //第二轮
        for (int i = 2; i < arr.length; i++) {
            //遍历各组中所有的元素
            for (int j = i - 2; j >= 0 ; j -= 2) {
                //如果当前元素大于加上步长后的那个元素，说明需要交换
                if (arr[j] > arr[j+2]){
                    int temp = arr[j];
                    arr[j] = arr[j+2];
                    arr[j+2] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));

        for (int i = 1; i < arr.length; i++) {
            //遍历各组中所有的元素
            for (int j = i - 1; j >= 0 ; j -= 1) {
                //如果当前元素大于加上步长后的那个元素，说明需要交换
                if (arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));*/
    }
    //移位法
    public static void shellSort2(int[] arr){
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素，对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]){
                    while (j - gap >= 0 && temp < arr[j - gap]){
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    arr[j] = temp;
                }
            }
        }
    }
}
