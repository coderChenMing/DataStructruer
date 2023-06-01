package array_hash;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 给出一个整型数组 numbers 和一个目标值 target，请在数组中找出两个加起来等于目标值的数的下标，返回的下标按升序排列。
 * （注：返回的数组下标从1开始算起，保证target一定可以由数组里面2个数字相加得到）
 */
public class NC61_两数之和 {
    public static void main(String[] args) {
        /*
         *1.找出下标对应的值相加为target
         * 2.数组中存在唯一解
         * */
        int[] input = {3, 2, 4};
        System.out.println(Arrays.toString(twoSum(input, 6)));
        System.out.println(Arrays.toString(twoSum2(input, 6)));

    }

    /**
     * @param numbers int整型一维数组
     * @param target  int整型
     * @return int整型一维数组
     */
    public static int[] twoSum(int[] numbers, int target) {
        // write code here
        int n = numbers.length;
        int[] res = {-1, -1};
        //遍历数组

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // 判断相加是否等于target
                if (numbers[i] + numbers[j] == target) {
                    res[0] = i + 1;//返回的数组下标从1开始算起
                    res[1] = j + 1;//返回的数组下标从1开始算起
                    return res;
                }
            }
        }
        return res;
    }

    public static int[] twoSum2(int[] numbers, int target) {
        // 使用Map来降低时间复杂度，遍历数组，如果没有 （target - 当前值） 就将当前数字存入哈希表，如果有，返回该数字下标即可。
        // 哈希表可以优化第二遍循环中对元素的检索速度，
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            //将不包含target - numbers[i]，装入map中，包含的话直接返回下标
            if (hm.containsKey(target - numbers[i])) {
                return new int[]{hm.get(target - numbers[i]) + 1, i + 1};
            } else {
                hm.put(numbers[i], i);
            }
        }
        //throw new IllegalArgumentException("no solution");
        return new int[]{-1, -1};
    }
}
