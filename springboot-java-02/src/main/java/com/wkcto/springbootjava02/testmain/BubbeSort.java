package com.wkcto.springbootjava02.testmain;

import java.util.Arrays;

/**
 * ClassName:BubbeSort
 * package:com.wkcto.springbootjava02.testmain
 * Description:
 *
 * @Data:2018/8/3 10:58
 * @Auther:wangxin
 */
public class BubbeSort {
    public static void main(String[] args) {
//        int[] array = {11,9,4,5,7,2,6,3};
//        int[] arr = sort(array);
//        System.out.println(Arrays.toString(arr));
        double x = 2 % 3;
        System.out.println(x);
    }

    public static int[] sort(int[] array){
        int t = 0;
        for(int j = 0; j < array.length -1;j++) {
            for (int i = 0; i < array.length - (j +1); i++) {

                if (array[i] > array[i + 1]) {
                    t = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = t;
                }
            }
        }

        return array;
    }
}
