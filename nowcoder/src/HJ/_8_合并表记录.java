package HJ;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 数据表记录包含表索引index和数值value（int范围的正整数），请对表索引相同的记录进行合并，
 * 即将相同索引的数值进行求和运算，输出按照index值升序进行输出。
 * <p>
 * 提示:
 * 0 <= index <= 11111111
 * 1 <= value <= 100000
 * <p>
 * 示例
 * 输入四组
 * 0 1
 * 0 2
 * 1 2
 * 3 4
 * 输出
 * 0 3
 * 1 2
 * 3 4
 */
public class _8_合并表记录 {
    /**
     * next():
     * <p>
     * 1、一定要读取到有效字符后才可以结束输入。
     * 2、对输入有效字符之前遇到的空白，next() 方法会自动将其去掉。
     * 3、只有输入有效字符后才将其后面输入的空白作为分隔符或者结束符。
     * next() 不能得到带有空格的字符串。
     * nextLine()：
     * <p>
     * 1、以Enter为结束符,也就是说 nextLine()方法返回的是输入回车之前的所有字符。
     * 2、可以获得空白。
     */
    public static void main(String[] args) {
        /*Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int a = in.nextInt();
            int b = in.nextInt();
            System.out.println(a + b);
        }*/

        /*System.out.println("请输入数字：");
        Scanner scan = new Scanner(System.in);

        double sum = 0;
        int m = 0;

        while (scan.hasNextDouble()) {
            double x = scan.nextDouble();
            m = m + 1;
            sum = sum + x;
            System.out.println(m + "个数的和为" + sum);
            System.out.println(m + "个数的平均值是" + (sum / m));
        }
        scan.close();*/
        Scanner scanner = new Scanner(System.in);
        int tableSize = scanner.nextInt();
        Map<Integer, Integer> table = new HashMap<>(tableSize);
        for (int i = 0; i < tableSize; i++) {
            int key = scanner.nextInt();
            int value = scanner.nextInt();
            if (table.containsKey(key)) {
                table.put(key, table.get(key) + value);
            } else {
                table.put(key, value);
            }
        }
        for (Integer key : table.keySet()) {
            System.out.println( key + " " + table.get(key));
        }
    }
}
