package com.dataStructures.sort;

/**
 * @Author: md
 * @Date: 2020/8/13 19:55
 * 归并排序
 */
public class MergetSort {
    public static void main(String[] args) {
        //int[] arr = {8,4,5,7,1,3,6,2,0,15};
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            double random = Math.random() * 8000000;
            arr[i] = (int) (random);
        }
        long start = System.currentTimeMillis();
        int[] temp = new int[arr.length];
        mergeSort(arr,0,arr.length - 1,temp);
        long end = System.currentTimeMillis();
        System.out.println(end - start);



       // System.out.println(Arrays.toString(arr));
    }


    public static void mergeSort(int[] arr,int left,int right,int[] temp){
        if (left < right){
            int mid = (left + right) / 2;
            //向左递归
            mergeSort(arr,left,mid,temp);
            //向右
            mergeSort(arr,mid+1,right,temp);
            merge(arr,left,mid,right,temp);
        }
    }

    /**
     *  合并的方法
     * @param arr
     * @param mid 中间索引
     * @param right
     * @param temp 中转数组
     */
    public static void merge(int[] arr,int left,int mid,int right,int[] temp){
        int i = left;   //左边的初始索引
        int j = mid + 1; //右边的初始索引
        int t = 0;   //指向temp数组的当前索引

        //先把左右两边的有序数据按规则按照规则填充到temp数组
        //知道左右两边的有序序列，又一遍处理完毕为止
        while (i <= mid && j <= right){

            if (arr[i] <= arr[j]){
                temp[t] = arr[i];
                t+=1;
                i+=1;
            }else {
                temp[t] = arr[j];
                t+=1;
                j+=1;
            }
        }

        //吧有剩余数据的一遍的数据依次全部填充到temp
        while (i <= mid){ //左边的有序序列还有剩余
            temp[t] = arr[i];
            t+=1;
            i+=1;
        }
        while (j <= right){ //右边的有序序列还有剩余
            temp[t] = arr[j];
            t+=1;
            j+=1;
        }

        //将temp数组的元素拷贝到arr
        //并不是每次都拷贝8个
        t = 0;
        int tempLeft = left;
        //System.out.println("left:"+tempLeft+"right:"+right);
        while (tempLeft <= right){
            arr[tempLeft] = temp[t];
            t+=1;
            tempLeft+=1;
        }
    }
}
