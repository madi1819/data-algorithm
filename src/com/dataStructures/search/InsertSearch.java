package com.dataStructures.search;

/**
 * @Author: md
 * @Date: 2020/8/14 21:18
 */
public class InsertSearch {
    public static void main(String[] args) {
//        int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12,16,18,50,100000};
        int[] arr = {1,1,1,1,1,1,7,1000000000};
        System.out.println(insertSearch(arr, 0, arr.length - 1, 7));
    }

    public static int insertSearch(int[] arr, int left, int right, int value){
        System.out.println("111");
        if (left > right || value < arr[0] || value > arr[arr.length - 1]){
            return -1;
        }
        int mid = left + (right - left) * (value - arr[left]) / (arr[right] - arr[left]);

        if (arr[mid] == value){
            return mid;
        }else if (value < arr[mid]){
            return insertSearch(arr,left,mid - 1,value);
        }else{
            return insertSearch(arr,mid + 1,right,value);
        }



    }

}
