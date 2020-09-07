package com.dataStructures.search;

import java.util.Arrays;

/**
 * @Author: md
 * @Date: 2020/8/15 14:41
 */
public class FibonaqiSearch {
    public static void main(String[] args) {
        int[] arr = {1,8,10,89,1000,1234};

        System.out.println(fibSearch(arr, 1));
    }
    public static int maxSize = 20;
    //mid = low+F(k-1)-1,获取一个斐波那契数列
    public static int[] fib(){
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     * 非递归
     * @param arr
     * @param key
     * @return
     */
    public static int fibSearch(int[] arr,int key){
        int low = 0;
        int high = arr.length - 1;
        int k = 0; //表示斐波那契分割数值的下标
        int mid = 0;
        int[]  f= fib();
        //获取到斐波那契分割数值的下标
        while (high > f[k] - 1){
            k++;
        }
        //因为f[k]值可能待遇arr的长度，因此我们需要使用Arrays类。构造一个新的数组，并指向arr[]
        //不足的地方用0填充
        int[] temp = Arrays.copyOf(arr,f[k]);
        //实际上需要使用arr数组最后的数填充
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }
        //使用while循环处理
        while (low <= high){
            mid = low + f[k - 1] -1;
            if (key < temp[mid]){
                high = mid - 1;
                //全部元素 = 前面元素 + 后面元素
                //f[k] = f[k - 1] + f[k - 2]
                //因为前面有f[k-1]个元素，所以可以继续拆分f[k - 1] = f[k - 2] + f[k - 3]
                //即在f[k - 1]前面继续查找  k--
                //即下次循环mid = f[k - 1 - 1] - 1
                k --;
            }else if (key > temp[mid]){
                low = mid + 1;
                //全部元素 = 前面元素 + 后面元素
                //f[k] = f[k - 1] + f[k - 2]
                //因为后面有f[k-2]个元素，所以可以继续拆分f[k - 2] = f[k - 3] + f[k - 4]
                //即下次循环mid = f[k - 1 - 2] - 1
                k -= 2;
            }else {
                //需要确定返回那个下标
                if (mid <= high){
                    return mid;
                }else {
                    return high;
                }
            }
        }
        return -1;
    }
}
