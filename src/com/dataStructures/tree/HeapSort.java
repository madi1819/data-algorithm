package com.dataStructures.tree;

/**
 * @Author: md
 * @Date: 2020/8/20 19:42
 */
public class HeapSort {
    public static void main(String[] args) {
        //要求将数组进行升序
       // int[] arr = {4,6,8,5,9};
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            double random = Math.random() * 8000000;
            arr[i] = (int) (random);
        }
        long start = System.currentTimeMillis();
        heatSort(arr);
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }

    //堆排序方法
    public static void heatSort(int[] arr){
        /*adjustHeap(arr,1,arr.length);
        System.out.println(Arrays.toString(arr));
        adjustHeap(arr,0,arr.length);
        System.out.println(Arrays.toString(arr));*/
        for (int i = arr.length / 2 - 1; i >= 0 ; i--) {
            adjustHeap(arr,i,arr.length);
        }

        int temp;
        //将堆顶元素与末尾元素交换，将最大元素沉到数组末端
        for (int i = arr.length - 1; i > 0; i--) {
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0,i);
        }
        //System.out.println(Arrays.toString(arr));
    }

    //将一个数组（二叉树），调整成一个大顶堆

    /**
     *  功能：完成将以i对应的非叶子节点的树调整成大顶堆
     * @param arr 待调整数组
     * @param i 表示非叶子节点在数组中索引
     * @param length 表示对多少个元素继续调整，length是在逐渐减少
     */
    public static void adjustHeap(int[] arr,int i,int length){
        int temp = arr[i];
        //开始调整
        //1.j = i * 2 + 1 是i的左子节点
        for (int j = i * 2 + 1; j < length; j = j * 2 + 1) {
            if (j + 1 < length && arr[j] < arr[j+1]){
                j++;
            }
            if (arr[j] > temp){
                arr[i] = arr[j];
                i = j;
            }else {
                break;
            }
        }
        //当for结束后，已经将以i为父节点的树的最大值，放在了最顶上
        arr[i] = temp;//将temp的值放到调整后的位置
    }
}
