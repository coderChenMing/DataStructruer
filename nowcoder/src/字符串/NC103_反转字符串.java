package 字符串;

/**
 * 写出一个程序，接受一个字符串，然后输出该字符串反转后的字符串。（字符串长度不超过1000）
 */
public class NC103_反转字符串 {

    public static String solve(String str) {
        // write code here
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();
    }

    public static String solve2(String str) {
        // write code here
        char[] chars = str.toCharArray();
        int size = chars.length;
        for (int i = 0; i < (size >> 1); i++) {
            // 注意这里不能是i <= (size >> 1),因为等于的时候对于偶数个字符,又会被交换回去
            char ch = chars[i];
            chars[i] = chars[size - i - 1];
            chars[size - i - 1] = ch;

        }
        return new String(chars);
    }

    public static String solve3(String str) {
        // write code here
        char[] ans = str.toCharArray();
        int len = str.length();
        for(int i = 0 ; i < len ;i++)
        {
            ans[i] = str.charAt(len-1-i);
        }
        return new String(ans);
    }

    public static String solve4(String str) {
        char[] chars = str.toCharArray();
        int n = chars.length;
        for (int left = 0, right = n - 1; left < right; ++left, --right) {
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;
        }
        //链接：https://leetcode.cn/problems/reverse-string/solutions/439034/fan-zhuan-zi-fu-chuan-by-leetcode-solution/
        return new String(chars);
    }
    public static void main(String[] args) {
        System.out.println(solve("helloo"));
        System.out.println(solve2("worldd"));
        System.out.println(solve3("worldd"));
        System.out.println(solve4("worldd"));
    }
}
