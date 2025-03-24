package leetcode.question.string_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *@Question:  2523. Closest Prime Numbers in Range
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 52.64%
 *@Time  Complexity: O(R * log(log(R)) + R - L) R is right, L is left
 *@Space Complexity: O(R)
 */
/**
 * 第一部分：题目描述
 * ----------------------------------------------
 * 2523. Closest Prime Numbers in Range（范围内最接近的质数对）
 *
 * 给定两个整数 left 和 right，找出范围 [left, right] 内最接近的两个质数（即质数对），并返回这两个数。
 * 如果范围内质数数量少于 2 个，则返回 [-1, -1]。
 *
 * 示例：
 * 输入：left = 10, right = 19
 * 输出：[11, 13]
 * 解释：范围 [10, 19] 内的质数为 [11, 13, 17, 19]，最近的两个质数是 11 和 13。
 *
 * 输入：left = 4, right = 6
 * 输出：[-1, -1]
 * 解释：范围 [4, 6] 内没有两个质数，因此返回 [-1, -1]。
 */


/**
 * 第二部分：详细解题思路（包含超级详细的步骤和举例）
 * ----------------------------------------------
 * 1. 使用 **埃拉托色尼筛法（Sieve of Eratosthenes）** 预计算出 2 到 right 之间的所有质数
 *    - 质数的定义是：一个大于 1 且仅能被 1 和自身整除的整数。
 *    - 我们创建一个数组 sieve[]，长度为 right + 1，用于标记哪些数是质数。
 *    - 初始化时，默认所有数都是质数 sieve[i] = 1（假设所有数都是质数）。
 *    - 0 和 1 不是质数，设 sieve[0] = 0, sieve[1] = 0。
 *    - 从 2 开始，逐步筛去所有合数：
 *        - 若 sieve[i] = 1，表示 i 是质数，则将 i 的所有倍数标记为非质数（sieve[multiple] = 0）。
 *    - 示例：
 *        - right = 10 时，筛选后：
 *        - sieve = [0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0]
 *        - 表示 2、3、5、7 是质数。
 *
 * 2. 遍历范围 [left, right]，收集所有质数到一个列表 primeNumbers
 *    - 我们已经预计算了 sieve[]，可以快速判断某个数是否是质数：
 *        - 若 sieve[num] = 1，则 num 是质数，加入 primeNumbers。
 *    - 示例：
 *        - left = 10, right = 19
 *        - primeNumbers = [11, 13, 17, 19]
 *
 * 3. 找出 primeNumbers 中差值最小的两个质数对
 *    - 如果 primeNumbers.size() < 2，说明范围内没有足够的质数，返回 [-1, -1]。
 *    - 初始化 minDifference = Integer.MAX_VALUE，用于记录最小的质数差值。
 *    - 遍历 primeNumbers 列表，计算相邻质数的差值 difference：
 *        - 若 difference < minDifference，则更新最近质数对。
 *    - 示例：
 *        - primeNumbers = [11, 13, 17, 19]
 *        - 计算：
 *            - 13 - 11 = 2
 *            - 17 - 13 = 4
 *            - 19 - 17 = 2
 *        - 最小差值是 2，最近的质数对是 [11, 13]。
 *
 * 4. 返回最终找到的最近质数对。
 *
 * ----------------------------------------------
 * 详细示例：
 *
 * 假设输入 left = 10, right = 19：
 * 1. 计算 sieve[]：
 *    sieve = [0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1]
 *    → 质数为 [2, 3, 5, 7, 11, 13, 17, 19]
 * 2. 过滤 [10, 19] 范围内的质数：
 *    → primeNumbers = [11, 13, 17, 19]
 * 3. 计算相邻质数差值：
 *    - 13 - 11 = 2
 *    - 17 - 13 = 4
 *    - 19 - 17 = 2
 * 4. 返回最小差值的质数对 [11, 13]。
 */


/**
 * 第三部分：时间和空间复杂度分析
 * ----------------------------------------------
 * 1. 质数筛选（埃拉托色尼筛法）
 *    - **时间复杂度：O(R log log R)**（埃拉托色尼筛法是最优化的筛选质数方法）
 *    - **空间复杂度：O(R)**（需要一个长度为 R + 1 的数组来存储筛选结果）
 *
 * 2. 遍历 [left, right] 过滤质数
 *    - **时间复杂度：O(R - L)**（只遍历目标范围）
 *    - **空间复杂度：O(R - L)**（存储目标范围内的质数）
 *
 * 3. 计算最近质数对
 *    - **时间复杂度：O(N)**，其中 N 是 [left, right] 范围内的质数个数（通常很小）
 *    - **空间复杂度：O(1)**（仅存储两个整数）
 *
 * **总时间复杂度：O(R log log R + R - L)**
 * **总空间复杂度：O(R)**
 *
 * 这使得算法在大范围查询时仍然保持高效。
 */


public class LeetCode_2523_ClosestPrimeNumbersInRange{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int[] closestPrimes(int left, int right) {
            // Step 1: 使用埃拉托色尼筛法 (Sieve of Eratosthenes) 获取范围 [2, right] 内的所有质数
            int[] sieveArray = sieve(right);

            // 存储在范围 [left, right] 内的所有质数
            List<Integer> primeNumbers = new ArrayList<>();
            for (int num = left; num <= right; num++) {
                // 如果当前数字是质数，则加入 primeNumbers 列表
                if (sieveArray[num] == 1) {
                    primeNumbers.add(num);
                }
            }

            // Step 2: 在 primeNumbers 中寻找最近的两个质数对
            // 如果质数的数量小于 2，则无法形成最近质数对，返回 {-1, -1}
            if (primeNumbers.size() < 2) return new int[] { -1, -1 };

            int minDifference = Integer.MAX_VALUE; // 记录最小的质数差
            int[] closestPair = new int[2]; // 记录最近的质数对
            Arrays.fill(closestPair, -1); // 初始化数组

            // 遍历 primeNumbers 列表，找出最近的两个质数
            for (int index = 1; index < primeNumbers.size(); index++) {
                int difference = primeNumbers.get(index) - primeNumbers.get(index - 1); // 计算相邻质数的差值
                if (difference < minDifference) { // 如果找到更小的差值，则更新
                    minDifference = difference;
                    closestPair[0] = primeNumbers.get(index - 1);
                    closestPair[1] = primeNumbers.get(index);
                }
            }

            return closestPair;
        }

        private int[] sieve(int upperLimit) {
            // 初始化一个数组 sieve，表示 0 到 upperLimit 之间的所有整数
            // sieve[i] = 1 表示 i 是质数，sieve[i] = 0 表示 i 不是质数
            int[] sieve = new int[upperLimit + 1];
            Arrays.fill(sieve, 1); // 初始时假设所有数都是质数

            // 0 和 1 不是质数，显式标记
            sieve[0] = 0;
            sieve[1] = 0;

            // 使用埃拉托色尼筛法标记所有的合数
            for (int number = 2; number * number <= upperLimit; number++) {
                if (sieve[number] == 1) { // 如果当前数是质数
                    // 将 number 的所有倍数标记为非质数
                    for (int multiple = number * number; multiple <= upperLimit; multiple += number) {
                        sieve[multiple] = 0; // 标记为非质数
                    }
                }
            }
            return sieve;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_2523_ClosestPrimeNumbersInRange().new Solution();

        // 测试案例 1：范围 [10, 19]，最近的两个质数是 (11, 13)
        int[] result1 = solution.closestPrimes(10, 19);
        System.out.println(Arrays.toString(result1)); // 预期输出: [11, 13]

        // 测试案例 2：范围 [4, 6]，无两个质数对，返回 {-1, -1}
        int[] result2 = solution.closestPrimes(4, 6);
        System.out.println(Arrays.toString(result2)); // 预期输出: [-1, -1]

        // 测试案例 3：范围 [1, 10]，最近的两个质数是 (2, 3)
        int[] result3 = solution.closestPrimes(1, 10);
        System.out.println(Arrays.toString(result3)); // 预期输出: [2, 3]

        // 测试案例 4：范围 [20, 50]，最近的两个质数是 (29, 31)
        int[] result4 = solution.closestPrimes(20, 50);
        System.out.println(Arrays.toString(result4)); // 预期输出: [29, 31]

        // 测试案例 5：范围 [100, 110]，最近的两个质数是 (101, 103)
        int[] result5 = solution.closestPrimes(100, 110);
        System.out.println(Arrays.toString(result5)); // 预期输出: [101, 103]
    }
}

/**
Given two positive integers left and right, find the two integers num1 and num2 
such that: 

 
 left <= num1 < num2 <= right . 
 Both num1 and num2 are prime numbers. 
 num2 - num1 is the minimum amongst all other pairs satisfying the above 
conditions. 
 

 Return the positive integer array ans = [num1, num2]. If there are multiple 
pairs satisfying these conditions, return the one with the smallest num1 value. If 
no such numbers exist, return [-1, -1]. 

 
 Example 1: 

 
Input: left = 10, right = 19
Output: [11,13]
Explanation: The prime numbers between 10 and 19 are 11, 13, 17, and 19.
The closest gap between any pair is 2, which can be achieved by [11,13] or [17,1
9].
Since 11 is smaller than 17, we return the first pair.
 

 Example 2: 

 
Input: left = 4, right = 6
Output: [-1,-1]
Explanation: There exists only one prime number in the given range, so the 
conditions cannot be satisfied.
 

 
 Constraints: 

 
 1 <= left <= right <= 10⁶ 
 

 
 

 Related Topics Math Number Theory 👍 872 👎 73

*/