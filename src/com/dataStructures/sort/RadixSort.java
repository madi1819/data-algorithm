package com.dataStructures.sort;

/**
 * @Author: md
 * @Date: 2020/8/14 16:21
 * 基数排序
 */
public class RadixSort {

    public static void main(String[] args) {

        //int[] arr = {53,3,542,748,14,214,15,659};
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            double random = Math.random() * 8000000;
            arr[i] = (int) (random);
        }
        long start = System.currentTimeMillis();
        int[] temp = new int[arr.length];
        radixSort(arr);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void radixSort(int[] arr){

        //定义一个二维数组表述10个桶，每个桶都是2维数组
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶实际存放了多少个数据，我们定义一个一维数组来记录各个桶每次放入的数据个数
        int[] bucketCounts = new int[10];

        //得到最大的位数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max){
                max = arr[i];
            }
        }
        int maxLength = (max + "").length();
        int index;
        for (int i = 0 , n = 1; i < maxLength; i++,n *= 10) {
            //第一轮
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的个为数值
                int digitOfElement = arr[j] / (int)Math.pow(10,i) % 10;
                //放入对应桶
                bucket[digitOfElement][bucketCounts[digitOfElement]] = arr[j];
                bucketCounts[digitOfElement]++;
            }
            //把数据取出来放入原数组
            index = 0;
            //遍历每一个桶，将桶的数据放到原数组
            for (int k = 0; k < bucket.length; k++) {
                //桶有数据才放入
                if (bucketCounts[k] != 0){
                    //循环
                    for (int l = 0; l < bucketCounts[k]; l++) {
                        arr[index] = bucket[k][l];
                        index++;
                    }
                }
                //第一轮处理后需要将每个bucketCounts[k] = 0
                bucketCounts[k] = 0;
            }
           // System.out.println(Arrays.toString(arr));
        }

//        //第一轮
//        for (int j = 0; j < arr.length; j++) {
//            //取出每个元素的个为数值
//            int digitOfElement = arr[j] % 10;
//            //放入对应桶
//            bucket[digitOfElement][bucketCounts[digitOfElement]] = arr[j];
//            bucketCounts[digitOfElement]++;
//        }
//        //把数据取出来放入原数组
//        int index = 0;
//        //遍历每一个桶，将桶的数据放到原数组
//        for (int k = 0; k < bucket.length; k++) {
//            //桶有数据才放入
//            if (bucketCounts[k] != 0){
//                //循环
//                for (int l = 0; l < bucketCounts[k]; l++) {
//                    arr[index] = bucket[k][l];
//                    index++;
//                }
//            }
//            //第一轮处理后需要将每个bucketCounts[k] = 0
//            bucketCounts[k] = 0;
//        }
//        System.out.println(Arrays.toString(arr));
//
//        //第二轮
//        for (int j = 0; j < arr.length; j++) {
//            //取出每个元素的个为数值
//            int digitOfElement = arr[j] /10 % 10;
//            //放入对应桶
//            bucket[digitOfElement][bucketCounts[digitOfElement]] = arr[j];
//            bucketCounts[digitOfElement]++;
//        }
//        //把数据取出来放入原数组
//        //遍历每一个桶，将桶的数据放到原数组
//        index = 0;
//        for (int k = 0; k < bucket.length; k++) {
//            //桶有数据才放入
//            if (bucketCounts[k] != 0){
//                //循环
//                for (int l = 0; l < bucketCounts[k]; l++) {
//                    arr[index] = bucket[k][l];
//                    index++;
//                }
//            }
//            bucketCounts[k] = 0;
//        }
//        System.out.println(Arrays.toString(arr));
//
//
//        //第三轮
//        for (int j = 0; j < arr.length; j++) {
//            //取出每个元素的个为数值
//            int digitOfElement = arr[j] /10 / 10% 10;
//            //放入对应桶
//            bucket[digitOfElement][bucketCounts[digitOfElement]] = arr[j];
//            bucketCounts[digitOfElement]++;
//        }
//        //把数据取出来放入原数组
//        //遍历每一个桶，将桶的数据放到原数组
//        index = 0;
//        for (int k = 0; k < bucket.length; k++) {
//            //桶有数据才放入
//            if (bucketCounts[k] != 0){
//                //循环
//                for (int l = 0; l < bucketCounts[k]; l++) {
//                    arr[index] = bucket[k][l];
//                    index++;
//                }
//            }
//        }
//        System.out.println(Arrays.toString(arr));
    }


}
