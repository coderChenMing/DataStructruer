package 位运算;

/**
 * 给定一个十进制数 M ，以及需要转换的进制数 N 。将十进制数 M 转化为 N 进制数。
 * 当 N 大于 10 以后， 应在结果中使用大写字母表示大于 10 的一位，如 'A' 表示此位为 10 ， 'B' 表示此位为 11 。
 * 若 M 为负数，应在结果中保留负号。
 * M<=10^8 ,2<=N<=16
 */
public class NC112_进制转换 {
    /**
     * 进制转换
     *
     * @param M int整型 给定整数
     * @param N int整型 转换到的进制
     * @return string字符串
     */
    public static String solve(int M, int N) {
        // write code here
        // 解题思路

        /*
        * 1.如果M==0,直接返回"0"
        * 2.如果M<0,先取|M|,求出进制数,然后前面加-号
        * 3.进制转换算法：求出N进制数就是使用除法和求余运算
        *
        * */
        if (M == 0) {
            return "0";
        }
        boolean flag = false; //记录是否负数
        if (M < 0) {
            flag = true;
            M = -M;
        }
        String jz = "0123456789ABCDEF";//对应进制的某一位
        StringBuffer res = new StringBuffer();//返回最终的结果
        while (M != 0) {//就对应转换为N进制的逆序样子
            res.append(jz.charAt(M % N));
            M /= N;//退出循环
        }
        res.reverse();//逆序一下才是对应的N进制
        if (flag) res.insert(0, "-");//如果是负数就在头位置插入一个-号
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(NC112_进制转换.solve(161, 16));
    }
}
