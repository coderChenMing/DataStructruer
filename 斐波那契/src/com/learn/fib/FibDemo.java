package com.learn.fib;

/**
 * Project: DataStructruer
 * File Created at 2022-01-16 10:42:10:42
 * {@link }
 * <p>
 * <p>
 * 斐波那契数特点:
 * 0 1 1 2 3 5 8 .....
 * 第n个数= 第n-1 +第 n -2
 * <p>
 * 对比递归调用和循环调用执行时间
 *
 * @author <a href="mailto:">chenming</a>
 * @version 1.0.0
 * @type FibDemo.java
 * @desc
 * @date 2022/1/16 0016 10:42
 */
public class FibDemo {
    public static void main(String[] args) {
        fib1(0);
        fib2(64);
        fib3(64);
    }

    /**
     * 递归调用:第n个数= 第n-1 +第 n -2
     */
    public static int fib1(int n) {
        if (n < 1) {
            return n;
        }
        return fib1(n - 1) + fib1(n - 2);
    }

    /**
     * 第0个数等于0
     * 第1个数等于1
     * 第2个数等于1
     * 第3个数等于2
     * ....
     * 除去第0,1个数
     * 第2个数需要执行加法运算1次
     * 第3个数需要执行加法运算2次
     * ...
     * 第n个数需要执行加法运算n-1次
     */
    public static int fib2(int n) {
        // n-1:加法运算次数
        int first = 0;
        int second = 1;
        //int sum = 0;
        for (int i = 0; i < n - 1; i++) {
            /*sum = first + second;
            first = second;
            second = sum;*/
            //优化版
            /*second = first + second;
            first = second - first;*/
            // 最终版
            second += first;
            first = second - first;
        }
        System.out.printf("第%d个数等于%d \n", n, second);
        return second;
    }

    /**
     * 数学公式法
     */
    public static int fib3(int n) {
        double c = Math.sqrt(5);
        System.out.printf("第%d个数等于%d", n, (int) ((Math.pow((1 + c) / 2, n) - Math.pow((1 - c) / 2, n)) / c));
        return (int) ((Math.pow((1 + c) / 2, n) - Math.pow((1 - c) / 2, n)) / c);
    }
}
