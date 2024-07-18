package leetcode.question.dp;
/**
 *@Question:  629. K Inverse Pairs Array
 *@Difficulty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 87.76%
 *@Time  Complexity: O(N * K * min(N, K)) for solution1, O(N * K) for solution2, and solution3
 *@Space Complexity: O(N * K) for solution 1, and solution2, O(K) for solution3
 */

/**
 * ### 解题思路
 *
 * 题目要求找到长度为 `n` 的数组中恰好有 `k` 个逆序对的所有可能的排列数量。
 * 可以使用动态规划的方法来解决这个问题。我们将介绍三种解决方案：自顶向下的记忆化搜索，自底向上的动态规划，以及自底向上的动态规划结合空间优化。
 *
 * #### 解决方案1：自顶向下的记忆化搜索
 *
 * 1. **定义状态**：
 *    - `dp(n, k)` 表示长度为 `n` 的数组中恰好有 `k` 个逆序对的所有排列数量。
 *
 * 2. **状态转移方程**：
 *    - 如果 `n == 0`，返回 `0`，因为没有数。
 *    - 如果 `k == 0`，返回 `1`，因为没有逆序对。
 *    - 遍历 `i` 从 `0` 到 `min(k, n-1)`，递归调用 `dp(n-1, k-i)`，累加结果并取模。
 *
 * 3. **缓存**：
 *    - 使用一个二维数组 `memo` 来缓存中间结果，避免重复计算。
 *
 * 4. **复杂度分析**：
 *    - **时间复杂度**：`O(N * K * min(N, K))`，其中 `N` 是数组的长度，`K` 是逆序对的数量，最坏情况下会遍历所有可能的子问题。
 *    - **空间复杂度**：`O(N * K)`，用于存储缓存的结果。
 *
 * #### 解决方案2：自底向上的动态规划
 *
 * 1. **定义状态**：
 *    - `dp[i][j]` 表示长度为 `i` 的数组中恰好有 `j` 个逆序对的所有排列数量。
 *
 * 2. **初始化**：
 *    - `dp[0][0] = 1`，即空数组有一种排列，没有逆序对。
 *    - `dp[i][0] = 1`，因为任何长度为 `i` 的数组如果不要求逆序对，只有一种升序排列方式。
 *
 * 3. **状态转移方程**：
 *    - `dp[i][j] = dp[i-1][j] + dp[i-1][j-1] + ... + dp[i-1][j-(i-1)]`
 *    - 为了优化计算，可以使用前缀和技巧：
 *      - `dp[i][j] = (dp[i][j-1] + dp[i-1][j] - (j-i >= 0 ? dp[i-1][j-i] : 0)) % M`
 *
 * 4. **复杂度分析**：
 *    - **时间复杂度**：`O(N * K)`，因为每个状态只需要常数时间的计算。
 *    - **空间复杂度**：`O(N * K)`，用于存储DP数组。
 *
 * #### 解决方案3：自底向上的动态规划 + 空间优化
 *
 * 1. **定义状态**：
 *    - 同上，但为了节省空间，使用一维数组来存储状态。
 *
 * 2. **状态转移方程**：
 *    - 类似于解决方案2，但使用滚动数组来优化空间。
 *    - `temp[j] = (temp[j-1] + dp[j] - (j-i >= 0 ? dp[j-i] : 0)) % M`
 *
 * 3. **复杂度分析**：
 *    - **时间复杂度**：`O(N * K)`，同上。
 *    - **空间复杂度**：`O(K)`，只需要一个大小为 `K` 的数组来存储状态。
 *
 * ### 总结
 *
 * - **解决方案1** 适用于较小的 `N` 和 `K`，因为时间复杂度较高。
 * - **解决方案2** 是标准的动态规划方法，适用于较大的 `N` 和 `K`。
 * - **解决方案3** 是对解决方案2的优化，适用于空间受限的情况。
 *
 * 通过动态规划，我们可以有效地计算长度为 `n` 的数组中恰好有 `k` 个逆序对的所有排列数量。
 */

public class LeetCode_629_KInversePairsArray{

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        // 使用缓存来保存计算过的结果
        Integer[][] memo = new Integer[1001][1001];

        // 解决方案1：自顶向下的动态规划
        public int kInversePairs1(int n, int k) {
            // 如果n为0，返回0，因为没有数
            if (n == 0)
                return 0;
            // 如果k为0，返回1，因为没有逆序对
            if (k == 0)
                return 1;
            // 如果结果已缓存，直接返回
            if (memo[n][k] != null)
                return memo[n][k];

            int inv = 0;
            // 计算当前n和k下的逆序对数量
            for (int i = 0; i <= Math.min(k, n - 1); i++)
                inv = (inv + kInversePairs(n - 1, k - i)) % 1000000007;
            // 缓存计算结果
            memo[n][k] = inv;
            return inv;
        }

        // 解决方案2：自底向上的动态规划
        public int kInversePairs(int n, int k) {
            // 定义dp数组
            int[][] dp = new int[n + 1][k + 1];
            int M = 1000000007;
            // 初始化dp数组
            for (int i = 1; i <= n; i++) {
                for (int j = 0; j <= k; j++) {
                    // 如果j为0，只有一种情况
                    if (j == 0)
                        dp[i][j] = 1;
                    else {
                        // 计算dp值
                        int val = (dp[i - 1][j] + M - ((j - i) >= 0 ? dp[i - 1][j - i] : 0)) % M;
                        dp[i][j] = (dp[i][j - 1] + val) % M;
                    }
                }
            }
            // 返回结果
            return ((dp[n][k] + M - (k > 0 ? dp[n][k - 1] : 0)) % M);
        }

        // 解决方案3：自底向上的动态规划 + 空间优化
        public int kInversePairs3(int n, int k) {
            // 定义dp数组
            int[] dp = new int[k + 1];
            int M = 1000000007;
            // 初始化dp数组
            for (int i = 1; i <= n; i++) {
                int[] temp = new int[k + 1];
                temp[0] = 1;
                for (int j = 1; j <= k ; j++) {
                    // 计算dp值
                    int val = (dp[j] + M - ((j - i) >= 0 ? dp[j - i] : 0)) % M;
                    temp[j] = (temp[j - 1] + val) % M;
                }
                dp = temp;
            }
            // 返回结果
            return ((dp[k] + M - (k > 0 ? dp[k - 1] : 0)) % M);
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_629_KInversePairsArray().new Solution();
        // 测试样例1：n = 3, k = 0，期望结果为1
        System.out.println(solution.kInversePairs(3, 0)); // 输出：1

        // 测试样例2：n = 3, k = 1，期望结果为2
        System.out.println(solution.kInversePairs(3, 1)); // 输出：2

        // 测试样例3：n = 3, k = 2，期望结果为2
        System.out.println(solution.kInversePairs(3, 2)); // 输出：2

        // 测试样例4：n = 3, k = 3，期望结果为1
        System.out.println(solution.kInversePairs(3, 3)); // 输出：1
    }
}

/**
For an integer array nums, an inverse pair is a pair of integers [i, j] where 0 
<= i < j < nums.length and nums[i] > nums[j]. 

 Given two integers n and k, return the number of different arrays consisting 
of numbers from 1 to n such that there are exactly k inverse pairs. Since the 
answer can be huge, return it modulo 10⁹ + 7. 

 
 Example 1: 

 
Input: n = 3, k = 0
Output: 1
Explanation: Only the array [1,2,3] which consists of numbers from 1 to 3 has 
exactly 0 inverse pairs.
 

 Example 2: 

 
Input: n = 3, k = 1
Output: 2
Explanation: The array [1,3,2] and [2,1,3] have exactly 1 inverse pair.
 

 
 Constraints: 

 
 1 <= n <= 1000 
 0 <= k <= 1000 
 

 Related Topics Dynamic Programming 👍 2667 👎 323

*/