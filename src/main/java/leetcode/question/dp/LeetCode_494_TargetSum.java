package leetcode.question.dp;

import java.util.Arrays;

/**
 *@Question:  494. Target Sum
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 77.84%
 *@Time Complexity: O(N * totalSum) for all solutions
 *@Space Complexity: O(N * totalSum) for solution1 and 2, O(totalSum) for solution3
 */
/**
 * 题目描述：
 * 494. Target Sum
 *
 * 给定一个整数数组 `nums` 和一个目标值 `target`，可以使用 `+` 或 `-` 为数组中的每个元素添加符号，
 * 计算 **有多少种不同的表达式** 可以使得最终结果等于 `target`。
 *
 * **示例 1**
 * ```plaintext
 * 输入: nums = [1, 1, 1, 1, 1], target = 3
 * 输出: 5
 * 解释: 一共有 5 种方法可以使表达式等于 3：
 * - 1 + 1 + 1 + 1 - 1 = 3
 * - 1 + 1 + 1 - 1 + 1 = 3
 * - 1 + 1 - 1 + 1 + 1 = 3
 * - 1 - 1 + 1 + 1 + 1 = 3
 * - -1 + 1 + 1 + 1 + 1 = 3
 * ```
 *
 * **示例 2**
 * ```plaintext
 * 输入: nums = [1], target = 1
 * 输出: 1
 * ```
 *
 * ---
 *
 * **解题思路**
 *
 * 这道题目可以转换为 **背包问题 (Subset Sum Problem)**，本质是：
 * **是否存在一种选取方式，使得所有选中的数之和等于目标值 `target`？**
 *
 * **数学推导**
 * - 设 `P` 表示选 `+` 的数字的总和，`N` 表示选 `-` 的数字的总和：
 *   - `P - N = target`
 *   - `P + N = sum(nums)`
 *   - 两个方程相加可得：`P = (sum(nums) + target) / 2`
 * - **结论**：问题转换为 **从 `nums` 选取子集，使得总和等于 `(sum(nums) + target) / 2`**。
 * - 这就是一个 **0-1 背包问题**，可使用 **动态规划** 解决。
 *
 * **方法 1：递归 + 记忆化搜索（Top-down DP）**
 * - 用递归函数 `dfs(i, sum)` 计算从索引 `i` 开始的子数组，能否形成 `sum`。
 * - 递归公式：
 *   ```plaintext
 *   dfs(i, sum) = dfs(i + 1, sum - nums[i]) + dfs(i + 1, sum + nums[i])
 *   ```
 * - **优化：** 记忆化搜索，使用 `memo[i][sum]` 记录已经计算过的结果，避免重复计算。
 * - **时间复杂度：O(N * sum(nums))**
 * - **空间复杂度：O(N * sum(nums))**
 *
 * **方法 2：动态规划（Bottom-up DP）**
 * - 定义 `dp[i][j]`：前 `i` 个数中，能否选出若干个数，使得总和为 `j`。
 * - 状态转移方程：
 *   ```plaintext
 *   dp[i][j] = dp[i-1][j]  (不选当前数)
 *   dp[i][j] = dp[i-1][j] + dp[i-1][j - nums[i-1]]  (选当前数)
 *   ```
 * - **时间复杂度：O(N * sum(nums))**
 * - **空间复杂度：O(N * sum(nums))**
 *
 * **方法 3：空间优化的动态规划**
 * - 由于 `dp[i][j]` 只依赖于 `dp[i-1][j]`，可以使用 **滚动数组** 优化。
 * - 只使用 **一维数组 `dp[j]`**，从 **后向前更新**，避免覆盖状态：
 *   ```plaintext
 *   dp[j] = dp[j] + dp[j - nums[i]]
 *   ```
 * - **时间复杂度：O(N * sum(nums))**
 * - **空间复杂度：O(sum(nums))**
 *
 * ---
 *
 * **举例分析**
 *
 * **输入:** `nums = [1, 1, 1, 1, 1], target = 3`
 *
 * **数学转换**
 * ```plaintext
 * sum(nums) = 5
 * P = (5 + 3) / 2 = 4
 * ```
 *
 * **动态规划表**
 * ```plaintext
 * dp[0] = 1  (只有一种方式形成 0，即什么都不选)
 * 处理 1:
 * dp[1] = 1
 * 处理 1:
 * dp[2] = dp[2] + dp[1] = 1
 * 处理 1:
 * dp[3] = dp[3] + dp[2] = 1
 * 处理 1:
 * dp[4] = dp[4] + dp[3] = 1
 * ```
 *
 * **最终 `dp[4] = 5`，说明有 5 种方法可以形成 4**
 *
 * ---
 *
 * **时间复杂度分析**
 * - **递归 + 记忆化搜索：O(N * sum(nums))**
 * - **动态规划：O(N * sum(nums))**
 * - **空间优化动态规划：O(sum(nums))**
 * - **总时间复杂度：O(N * sum(nums))**
 *
 * **空间复杂度分析**
 * - **递归 + 记忆化搜索：O(N * sum(nums))**
 * - **普通 DP：O(N * sum(nums))**
 * - **空间优化 DP：O(sum(nums))**
 * - **总空间复杂度：O(sum(nums))**
 */

public class LeetCode_494_TargetSum{

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        /**
         * 递归 + 记忆化搜索 (Top-down DP)
         * 计算从 currentIndex 开始，使用 nums 数组中的元素，能否形成 target 目标和
         */
        private int calculateWays(
                int[] nums,
                int currentIndex,
                int currentSum,
                int target,
                int[][] memo
        ) {
            // 递归终止条件：遍历到数组末尾
            if (currentIndex == nums.length) {
                // 如果当前和等于目标值，则找到一种可行解，返回 1
                if (currentSum == target) {
                    return 1;
                } else {
                    return 0;
                }
            } else {
                // 记忆化搜索：如果该状态已计算过，直接返回结果
                if (memo[currentIndex][currentSum + totalSum] != Integer.MIN_VALUE) {
                    return memo[currentIndex][currentSum + totalSum];
                }
                // 递归调用：选择当前数加法 or 选择当前数减法
                int add = calculateWays(nums, currentIndex + 1, currentSum + nums[currentIndex], target, memo);
                int subtract = calculateWays(nums, currentIndex + 1, currentSum - nums[currentIndex], target, memo);
                // 记忆化存储计算结果
                memo[currentIndex][currentSum + totalSum] = add + subtract;
                return memo[currentIndex][currentSum + totalSum];
            }
        }

        // 记录所有元素的总和
        int totalSum;

        /**
         * 解法 1: 递归 + 记忆化搜索
         */
        public int findTargetSumWays1(int[] nums, int target) {
            // 计算数组所有元素的总和
            totalSum = Arrays.stream(nums).sum();
            // 记忆化存储数组
            int[][] memo = new int[nums.length][2 * totalSum + 1];
            for (int[] row : memo) {
                Arrays.fill(row, Integer.MIN_VALUE);
            }
            // 递归求解
            return calculateWays(nums, 0, 0, target, memo);
        }

        /**
         * 解法 2: 动态规划 (Bottom-up DP)
         */
        public int findTargetSumWays(int[] nums, int target) {
//            // 计算数组所有元素的总和
//            int totalSum = Arrays.stream(nums).sum();
//            // 定义 dp 数组：dp[i][j] 表示前 i 个数能否形成和 j
//            int[][] dp = new int[nums.length][2 * totalSum + 1];
//
//            // 初始化：第一行只有加上 nums[0] 和减去 nums[0] 是有效的
//            dp[0][nums[0] + totalSum] = 1;
//            dp[0][-nums[0] + totalSum] += 1;
//
//            // 填充 DP 表
//            for (int index = 1; index < nums.length; index++) {
//                for (int sum = -totalSum; sum <= totalSum; sum++) {
//                    if (dp[index - 1][sum + totalSum] > 0) {
//                        dp[index][sum + nums[index] + totalSum] += dp[index - 1][sum + totalSum];
//                        dp[index][sum - nums[index] + totalSum] += dp[index - 1][sum + totalSum];
//                    }
//                }
//            }
//            for(int[] i : dp){
//                System.out.println(Arrays.toString(i));
//            }
//
//            // 如果目标值超出范围，返回 0，否则返回计算结果
//            return Math.abs(target) > totalSum ? 0 : dp[nums.length - 1][target + totalSum];

            int sum = 0;
            for (int i : nums) {
                sum += i;
            }
            if (sum < target || (target + sum) % 2 == 1 || sum + target  < 0) {
                return 0;
            }
            //给定一个数target，在非负数组中挑选几个数，使他们的和为target，计算有多少种选取方式。恰好是个经典的背包问题。
            return count(nums, (target + sum) / 2);
        }

        private int count(int[] nums, int target) {
            int n = nums.length;
            int[][] dp = new int[n + 1][target + 1];

            for (int i = 0; i <= n; i++) {
                dp[i][0] = 1;
            }

            for (int i = 1; i <= n; i++) {
                for (int j = 0; j <= target; j++) {
                    if (j >= nums[i - 1]) {
                        dp[i][j] = dp[i - 1][j - nums[i - 1]] + dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }

            return dp[n][target];
        }


        /**
         * 解法 3: 基于解法 2 进行空间优化
         */
        public int findTargetSumWays2(int[] nums, int target) {
            // 计算数组所有元素的总和
            int totalSum = Arrays.stream(nums).sum();
            // 定义 dp 数组（仅使用一维数组进行状态压缩）
            int[] dp = new int[2 * totalSum + 1];

            // 初始化：第一行只有加上 nums[0] 和减去 nums[0] 是有效的
            dp[nums[0] + totalSum] = 1;
            dp[-nums[0] + totalSum] += 1;

            // 填充 DP 表
            for (int index = 1; index < nums.length; index++) {
                int[] next = new int[2 * totalSum + 1];
                for (int sum = -totalSum; sum <= totalSum; sum++) {
                    if (dp[sum + totalSum] > 0) {
                        next[sum + nums[index] + totalSum] += dp[sum + totalSum];
                        next[sum - nums[index] + totalSum] += dp[sum + totalSum];
                    }
                }
                dp = next;
            }

            // 如果目标值超出范围，返回 0，否则返回计算结果
            return Math.abs(target) > totalSum ? 0 : dp[target + totalSum];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_494_TargetSum().new Solution();

        // 测试样例 1
        int[] nums1 = {1, 1, 1, 1, 1};
        int target1 = 3;
        System.out.println(solution.findTargetSumWays(nums1, target1)); // 预期输出: 5

        // 测试样例 2
        int[] nums2 = {1};
        int target2 = 1;
        System.out.println(solution.findTargetSumWays(nums2, target2)); // 预期输出: 1

        // 测试样例 3
        int[] nums3 = {1, 2, 3, 4, 5};
        int target3 = 3;
        System.out.println(solution.findTargetSumWays(nums3, target3)); // 预期输出: 3

        // 测试样例 4
        int[] nums4 = {100};
        int target4 = -200;
        System.out.println(solution.findTargetSumWays(nums4, target4)); // 预期输出: 0

        // 测试样例 5
        int[] nums5 = {1, 2, 7, 9, 981};
        int target5 = 100000;
        System.out.println(solution.findTargetSumWays(nums5, target5)); // 预期输出: 0
    }
}

/**
You are given an integer array nums and an integer target. 

 You want to build an expression out of nums by adding one of the symbols '+' 
and '-' before each integer in nums and then concatenate all the integers. 

 
 For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 
and concatenate them to build the expression "+2-1". 
 

 Return the number of different expressions that you can build, which evaluates 
to target. 

 
 Example 1: 

 
Input: nums = [1,1,1,1,1], target = 3
Output: 5
Explanation: There are 5 ways to assign symbols to make the sum of nums be 
target 3.
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3
 

 Example 2: 

 
Input: nums = [1], target = 1
Output: 1
 

 
 Constraints: 

 
 1 <= nums.length <= 20 
 0 <= nums[i] <= 1000 
 0 <= sum(nums[i]) <= 1000 
 -1000 <= target <= 1000 
 

 Related Topics Array Dynamic Programming Backtracking 👍 11697 👎 389

*/