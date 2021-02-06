package com;

import java.io.FileNotFoundException;

/**
 * @author 周超
 * @since 2020/12/5 20:26
 */
public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(Integer.toBinaryString(-1<<29));
        System.out.println(Integer.toBinaryString(0<<29));
        System.out.println(Integer.toBinaryString(1<<29));
        System.out.println(Integer.toBinaryString(2<<29));
        System.out.println(Integer.toBinaryString(3<<29));
    }
}
