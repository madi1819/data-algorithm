package com.dataStructures.search;

/**
 * @Author: md
 * @Date: 2020/8/14 20:11
 */
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {53,3,542,748,14,214,15,659};
        int i = seqSearch(arr, 14);
        System.out.println(i);
    }

    public static int seqSearch(int[] arr,int value){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value){
                return i;
            }
        }
        return -1;
    }
}
