package com.dataStructures.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: md
 * @Date: 2020/8/14 20:40
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12,16,18,50,100000};
        System.out.println(binarySearch1(arr, 0, arr.length - 1, 7));
    }

    public static int binarySearch(int[] arr,int left,int right,int value){
        if (left > right){
            return -1;
        }
        int mid = (left + right) / 2;
        if (arr[mid] == value){
            return mid;
        }else if (value < arr[mid]){
            return binarySearch(arr,left,mid - 1,value);
        }else{
            return binarySearch(arr,mid + 1,right,value);
        }
    }

    /**
     * 找到相同的多个元素
     * @param arr
     * @param left
     * @param right
     * @param value
     * @return
     */
    public static List binarySearch1(int[] arr, int left, int right, int value){
        System.out.println("111");
        if (left > right){
            return null;
        }
        int mid = (left + right) / 2;
        if (arr[mid] == value){
            List list = new ArrayList();
            list.add(mid);
            int temp = mid - 1;
            while (true){
                if (temp < 0 || arr[temp] != value){
                    break;
                }
                list.add(temp);
                temp -- ;
            }

            temp = mid + 1;
            while (true){
                if (temp > arr.length - 1 || arr[temp] != value){
                    break;
                }
                list.add(temp);
                temp ++ ;
            }
            return list;
        }else if (value < arr[mid]){
            return binarySearch1(arr,left,mid - 1,value);
        }else{
            return binarySearch1(arr,mid + 1,right,value);
        }
    }
}

