package 字符串;

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * <p>
 * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
 * <p>
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 */
public class _5_最长回文子串 {
    /**
     * 中心扩散法示例
     * 算法流程：
     * 每个字符都可以尝试作为中心点看，会出现两种情况：可能是类似 aba 的字符串，也可能是类似 abba 的情况
     * 只需要分别计算出以一个和两个字符作为中心点的子串，取出较大的长度即可
     * 从left到right开始向两边扩散、比较，如果相等则继续扩散比较
     * 如果不相等则剪枝，不用再继续扩散比较
     * 计算每次比较的回文子串长度，取最大
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);

        //链接：https://leetcode.cn/problems/longest-palindromic-substring/solutions/255195/zui-chang-hui-wen-zi-chuan-by-leetcode-solution/
    }

    public static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return right - left - 1;
    }

    public static void main(String[] args) {

    }
}
