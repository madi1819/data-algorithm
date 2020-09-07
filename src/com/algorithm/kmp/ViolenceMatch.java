package com.algorithm.kmp;

/**
 * @Author: md
 * @Date: 2020/9/1 21:11
 * 暴力匹配
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        String str1 = "abc adc adfadc";
        String str2 = "adc";
        int i = violenceMatch(str1, str2);
        System.out.println(i);
        System.out.println(str1.indexOf(str2));
    }

    public static int violenceMatch(String str1,String str2){
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Len = s1.length;
        int s2Len = s2.length;
        int i = 0;
        int j = 0;
        while (i < s1Len && j < s2Len){
            if (s1[i] == s2[j]){
                i++;
                j++;
            }else {
                i = i - (j - 1);
                j = 0;
            }
        }
        if ( j == s2Len){
            return i - j;
        }else {
            return -1;
        }
    }
}
