package com.algorithm.kmp;

/**
 * @Author: md
 * @Date: 2020/9/2 12:29
 * KMP算法
 */
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";

        int[] next = kmpNext(str2);
        System.out.println(kmpSearch(str1, str2, next));
    }

    //kmp搜索算法
    public static int kmpSearch(String str1,String str2,int[] next){
        //遍历
        for (int i = 0 , j = 0 ;i < str1.length(); i++) {
            //需要处理str1.charAt(i) ！= str2.charAt(j)
            //KMP核心
            while (j > 0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)){
                j++;
            }
            if (j == str2.length()){
                return i - j + 1;
            }
        }
        return -1;
    }

    //获取到一个字符串（子串）的部分匹配值
    public static int[] kmpNext(String dest){
        //创建一个next数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0;//如果字符串长度为1,部分匹配值为0
        for (int i = 1 , j = 0; i < dest.length(); i++) {
            while (j > 0 && dest.charAt(i) != dest.charAt(j)){
                j = next[j - 1];
            }
            if (dest.charAt(i) == dest.charAt(j)){
                j ++;
            }
            next[i] = j;
        }
        return next;
    }
}
