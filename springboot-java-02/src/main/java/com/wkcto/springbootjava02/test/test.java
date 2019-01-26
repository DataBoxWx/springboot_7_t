package com.wkcto.springbootjava02.test;

/**
 * ClassName:test
 * package:com.wkcto.springbootjava02.test
 * Description:
 *
 * @Data:2018/7/26 10:51
 * @Auther:wangxin
 */
public class test {
    public static void main(String[] args) {
        System.out.println(test(5));
    }

    public static   int test( int i){
        if(i == 1){
            return 1;
        }
        return i * test(i - 1);
    }
}
