package com.wkcto.springbootjava02.testmain;

import java.util.Arrays;

/**
 * ClassName:SelectSort
 * package:com.wkcto.springbootjava02.testmain
 * Description:
 *
 * @Data:2018/8/3 11:15
 * @Auther:wangxin
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] array = {11,9,4,5,7,2,6,3};
        int[] arr = sort(array);
        System.out.println(Arrays.toString(arr));
    }

    public static int[] sort(int[] arr){

        for(int i = 0; i < arr.length; i++){
            int max = 0;
            for(int j = 1; j < arr.length - i ; j++){
                if(arr[j] > arr[max]){
                    max = j;
                }
            }
            int t = arr[arr.length -(1 + i)];
            arr[arr.length -(1 + i )] = arr[max];
            arr[max] = t;

        }

        return arr;
    }
}
