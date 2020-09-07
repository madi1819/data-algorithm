package com.algorithm.binarysearch;

/**
 * @Author: md
 * @Date: 2020/8/31 19:56
 */
public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int[] arr = {1,3,5,8,9,10,15,20,35};
        System.out.println(binarySearch(arr, 100));
    }

    //二分查找非递归
    public static int binarySearch(int[] arr,int target){
        int left = 0;
        int right = arr.length - 1;
        while (left <= right){
            int mid = (left + right) / 2;
            if (arr[mid] == target){
                return mid;
            }else if (arr[mid] < target){
                left = mid + 1;
            }else {
                right = mid -1;
            }
        }
        return -1;
    }
}
