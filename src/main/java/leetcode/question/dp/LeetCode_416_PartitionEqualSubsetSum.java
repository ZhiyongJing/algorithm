package leetcode.question.dp;
/**
 *@Question:  416. Partition Equal Subset Sum
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 60.21%
 *@Time Complexity: O(n * subSetSum), where n is the number of elements and subSetSum is the target sum.
 *@Space Complexity: O(n * subSetSum) for solution1 and solution2, O(subSetSum) for solution3.
 */
/**
 * 题目描述：
 * 416. Partition Equal Subset Sum
 *
 * 给定一个 **只包含正整数** 的数组 `nums`，判断是否可以将数组拆分成两个 **元素和相等** 的子集。
 *
 * **要求**
 * - 每个元素只能使用一次。
 * - **时间复杂度不能超过 O(N * sum/2)**，即 **动态规划** 是合理的解法。
 *
 * **示例 1**
 * ```plaintext
 * 输入: nums = [1, 5, 11, 5]
 * 输出: true
 * 解释: 可以拆分成 [1, 5, 5] 和 [11]，两者之和相等。
 * ```
 *
 * **示例 2**
 * ```plaintext
 * 输入: nums = [1, 2, 3, 5]
 * 输出: false
 * 解释: 无法拆分成两个和相等的子集。
 * ```
 *
 * ---
 *
 * **解题思路**
 *
 * 该问题可以转换为一个 **子集和问题 (Subset Sum Problem)**：
 * **是否存在一个子集，其元素和等于 `totalSum / 2`？**
 *
 * **1. 计算 `totalSum`**
 * - 如果 `totalSum` 是 **奇数**，那么直接返回 `false`，因为无法均分。
 * - 如果 `totalSum` 是 **偶数**，则问题转换为 **子集和问题**。
 * - 目标和 `subSetSum = totalSum / 2`。
 *
 * **2. 递归 + 记忆化搜索 (Top-down DP)**
 * - 递归函数 `dfs(nums, i, sum, memo)`：
 *   - `i` 是当前索引，`sum` 是目标子集和。
 *   - 选择 `nums[i]` 或者 **不选择 `nums[i]`**。
 *   - 递归求解 `dfs(nums, i - 1, sum - nums[i]) || dfs(nums, i - 1, sum)`
 * - 用 `memo[i][sum]` 存储子问题的结果，避免重复计算。
 *
 * **3. 动态规划 (Bottom-up DP)**
 * - 定义 `dp[i][j]`：表示是否可以从前 `i` 个元素中选出 **和为 `j` 的子集**。
 * - 状态转移方程：
 *   - `dp[i][j] = dp[i-1][j]`（不选当前元素）
 *   - `dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]]`（选当前元素）
 * - **最终答案**：`dp[n][subSetSum]`，`n` 是数组大小。
 *
 * **4. 空间优化**
 * - 由于 `dp[i][j]` 只依赖于 `dp[i-1][j]`，可以用 **滚动数组优化空间复杂度**。
 * - 只使用 **一维数组 `dp[j]`**，从 **后向前更新**，避免覆盖状态。
 *
 * ---
 *
 * **举例分析**
 *
 * **输入:** `nums = [1, 5, 11, 5]`
 *
 * **计算 `subSetSum = 11`**
 * ```plaintext
 * nums = [1, 5, 11, 5]
 * totalSum = 22 (even), subSetSum = 11
 * ```
 *
 * **动态规划表**
 * ```plaintext
 * 初始状态:
 * dp = [true, false, false, ..., false]  (dp[0] = true)
 *
 * 处理 1:
 * dp = [true, true, false, ..., false]
 *
 * 处理 5:
 * dp = [true, true, false, false, false, true, true, false, ..., false]
 *
 * 处理 11:
 * dp = [true, true, false, false, false, true, true, false, false, false, true]
 * ```
 *
 * **最终 `dp[11] = true`，说明可以找到子集和为 `11`**
 *
 * ---
 *
 * **5. 时间复杂度分析**
 * - **递归 + 记忆化搜索：O(N * subSetSum)**
 * - **动态规划：O(N * subSetSum)**
 * - **空间优化动态规划：O(subSetSum)**
 * - **总时间复杂度：O(N * subSetSum)**
 *
 * **6. 空间复杂度分析**
 * - **递归 + 记忆化搜索：O(N * subSetSum)**
 * - **普通 DP：O(N * subSetSum)**
 * - **空间优化 DP：O(subSetSum)**
 * - **总空间复杂度：O(subSetSum)**
 */


public class LeetCode_416_PartitionEqualSubsetSum{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // Solution 1: 递归 + 记忆化搜索 (Top-down DP)
        public boolean canPartition1(int[] nums) {
            int totalSum = 0;
            // 计算数组所有元素的总和
            for (int num : nums) {
                totalSum += num;
            }
            // 如果总和是奇数，则无法拆分成两个和相等的子集
            if (totalSum % 2 != 0) return false;
            int subSetSum = totalSum / 2;
            int n = nums.length;
            // 记忆化数组，memo[i][j] 表示是否可以从前 i 个元素中选出和为 j 的子集
            Boolean[][] memo = new Boolean[n + 1][subSetSum + 1];
            return dfs(nums, n - 1, subSetSum, memo);
        }

        public boolean dfs(int[] nums, int n, int subSetSum, Boolean[][] memo) {
            // 目标和为 0，则返回 true
            if (subSetSum == 0)
                return true;
            // 若没有元素或目标和变为负数，则返回 false
            if (n == 0 || subSetSum < 0)
                return false;
            // 如果当前子问题已经计算过，则直接返回存储的结果
            if (memo[n][subSetSum] != null)
                return memo[n][subSetSum];
            // 递归调用：选择当前元素或不选择
            boolean result = dfs(nums, n - 1, subSetSum - nums[n - 1], memo) ||
                    dfs(nums, n - 1, subSetSum, memo);
            // 存储结果
            memo[n][subSetSum] = result;
            return result;
        }

        // Solution 2: 动态规划 (Bottom-up DP)
        public boolean canPartition(int[] nums) {
            int totalSum = 0;
            // 计算数组所有元素的总和
            for (int num : nums) {
                totalSum += num;
            }
            // 如果总和是奇数，则无法拆分成两个和相等的子集
            if (totalSum % 2 != 0) return false;
            int subSetSum = totalSum / 2;
            int n = nums.length;
            // dp[i][j] 表示是否可以从前 i 个元素中选出和为 j 的子集
            boolean dp[][] = new boolean[n + 1][subSetSum + 1];
            dp[0][0] = true; // 空集和为 0

            for (int i = 1; i <= n; i++) {
                int curr = nums[i - 1]; // 当前元素
                for (int j = 0; j <= subSetSum; j++) {
                    if (j < curr)
                        dp[i][j] = dp[i - 1][j]; // 不能选当前元素
                    else
                        dp[i][j] = dp[i - 1][j] || dp[i - 1][j - curr]; // 选择或不选择当前元素
                }
            }
            return dp[n][subSetSum];
        }

        // Solution 3: 基于 Solution2 进行空间优化
        public boolean canPartition3(int[] nums) {
            if (nums.length == 0)
                return false;
            int totalSum = 0;
            // 计算数组所有元素的总和
            for (int num : nums) {
                totalSum += num;
            }
            // 如果总和是奇数，则无法拆分成两个和相等的子集
            if (totalSum % 2 != 0) return false;
            int subSetSum = totalSum / 2;
            // 采用一维数组 dp[j]，dp[j] 表示是否能选出和为 j 的子集
            boolean dp[] = new boolean[subSetSum + 1];
            dp[0] = true; // 空集和为 0

            for (int curr : nums) {
                for (int j = subSetSum; j >= curr; j--) {
                    dp[j] |= dp[j - curr]; // 选择当前元素或者不选择
                }
            }
            return dp[subSetSum];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_416_PartitionEqualSubsetSum().new Solution();

        // 测试样例 1
        int[] nums1 = {1, 5, 11, 5};
        System.out.println(solution.canPartition(nums1)); // 预期输出: true (可以分成 [1, 5, 5] 和 [11])

        // 测试样例 2
        int[] nums2 = {1, 2, 3, 5};
        System.out.println(solution.canPartition(nums2)); // 预期输出: false (无法分成两个和相等的子集)

        // 测试样例 3
        int[] nums3 = {2, 2, 3, 5};
        System.out.println(solution.canPartition(nums3)); // 预期输出: false

        // 测试样例 4
        int[] nums4 = {1, 2, 5};
        System.out.println(solution.canPartition(nums4)); // 预期输出: false

        // 测试样例 5
        int[] nums5 = {3, 3, 3, 3};
        System.out.println(solution.canPartition(nums5)); // 预期输出: true
    }
}

/**
Given an integer array nums, return true if you can partition the array into 
two subsets such that the sum of the elements in both subsets is equal or false 
otherwise. 

 
 Example 1: 

 
Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].
 

 Example 2: 

 
Input: nums = [1,2,3,5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.
 

 
 Constraints: 

 
 1 <= nums.length <= 200 
 1 <= nums[i] <= 100 
 

 Related Topics Array Dynamic Programming 👍 12718 👎 262

*/