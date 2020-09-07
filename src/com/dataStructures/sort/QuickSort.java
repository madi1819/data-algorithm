package com.dataStructures.sort;

/**
 * @Author: md
 * @Date: 2020/8/12 20:37
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            double random = Math.random() * 8000000;
            arr[i] = (int) (random);
        }
        long start = System.currentTimeMillis();
        quickSort1(arr,0,arr.length - 1);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
       // int[] arr = {1,2,3,4,1,2,3,8};
       // quickSort(arr,0,arr.length - 1);

       // System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr,int left,int right){
        int l = left;
        int r = right;
        int pivot = arr[(left + right) / 2];

        while (l < r){
            while (arr[l] < pivot){
                l ++;
            }
            while (arr[r] > pivot){
                r --;
            }
            if (l >= r){
                break;
            }
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            if (arr[l] == pivot){
                r -= 1;
            }
            if (arr[r] == pivot){
                l += 1;
            }
        }
        if (l == r){
            l += 1;
            r -= 1;
        }
        if (left < r){
            quickSort(arr,left,r);
        }
       if (right > l){
           quickSort(arr,l,right);
       }

    }


    public static void quickSort1(int[] arr,int left,int right){
        if (left > right){
            return;
        }
        int l = left;
        int r = right;
        int pivot = arr[left];
        while (l < r){
            while (arr[r] >= pivot && l < r){
                r --;
            }
            while (arr[l] <= pivot && l < r){
                l ++;
            }


            if (l<r){
                int temp = arr[l];
                arr[l] = arr[r];
                arr[r] = temp;
            }
        }
        arr[left] = arr[l];
        arr[l] = pivot;
        quickSort1(arr,left,l - 1);
        quickSort1(arr,l + 1,right);
    }
}
