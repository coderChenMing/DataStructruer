package com.learn;

/**
 * Project: DataStructruer
 * File Created at 2022-01-16 10:59:10:59
 * {@link }
 *
 * @author <a href="mailto:">chenming</a>
 * @version 1.0.0
 * @type ComlexDemo.java
 * @desc
 * @date 2022/1/16 0016 10:59
 */
public class ComlexDemo {
    public static void main(String[] args) {
        test(16);
    }

    /**
     * 时间复杂度:log2n
     * n=2打印1次
     * n=4打印2次
     * n=8打印3次
     * n=16打印4次
     * ......
     * n=2的m次方,打印m次
     *
     * */
    public static void test(int n) {
        while ((n = n / 2) > 0) {
            System.out.println("test");
        }
    }
}
