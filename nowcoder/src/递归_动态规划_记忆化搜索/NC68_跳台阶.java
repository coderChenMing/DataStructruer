package 递归_动态规划_记忆化搜索;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法（先后次序不同算不同的结果）
 */
public class NC68_跳台阶 {
    public static int jumpFloor(int target) {
        //递归实现
        if (target < 1) {
            return -1;
        }
        if (target == 1) {
            return 1;
        }
        if (target == 2) {
            return 2;
        }
        return jumpFloor(target - 1) + jumpFloor(target - 2);
    }

    public static int jumpFloor2(int target) {
        //递归实现
        if (target < 1) {
            return -1;
        }
        if (target == 1) {
            return 1;
        }
        if (target == 2) {
            return 2;
        }
        int res = 0;
        int a = 1;
        int b = 2;
        //因n=2时也为1，初始化的时候把a=0，b=1
        for(int i = 3; i <= target; i++){
            //第三项开始是前两项的和,然后保留最新的两项，更新数据相加
            res = (a + b);
            a = b;
            b = res;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(jumpFloor(40));
        System.out.println(jumpFloor2(40));
    }
}
