package com.welldig.xping.java;

import java.util.UUID;

/**
 * Created by xping.zong
 * Date: 2016/9/26
 * Time: 13:53
 * Description:
 */
public class Random {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {

            System.out.println(UUID.randomUUID().toString());
        }
        String s = digits(1L,4);
        System.out.println(s);
    }

    private static String digits(long val, int digits) {
        long hi = 1L << (digits * 4);
        return Long.toHexString(hi | (val & (hi - 1))).substring(1);
    }
}
