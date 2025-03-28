package leetcode.question.dp;

import java.util.Arrays;
/**
 *@Question:  322. Coin Change
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 75.86%
 *@Time  Complexity: O(S*N)
 *@Space Complexity: O(S)
 */

/*
 * 题目描述：
 * LeetCode 322 - Coin Change
 * 给定一个整数数组 `coins`，其中 `coins[i]` 表示不同面额的硬币，以及一个目标金额 `amount`。
 * 你需要计算 **拼凑出该金额所需的最少硬币个数**，如果无法拼凑出该金额，则返回 `-1`。
 *
 * **输入：**
 * - `int[] coins`：可选硬币的面额数组（无重复元素）。
 * - `int amount`：目标金额。
 *
 * **输出：**
 * - `int`：所需的最少硬币数量，如果无法拼凑则返回 `-1`。
 *
 * **示例：**
 * ```
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 *
 * 输入: coins = [2], amount = 3
 * 输出: -1
 *
 * 输入: coins = [1], amount = 0
 * 输出: 0
 * ```
 */

/*
 * 解题思路：
 * 该问题是 **完全背包问题** 的变种，需要计算出组成 `amount` 的 **最优解（最少硬币数）**。
 *
 * **方法 1：递归 + 记忆化搜索（Top-Down DP）**
 * ------------------------------------------------------
 * 1️⃣ **递归拆解问题**
 *    - 递归计算 `dp[amount]`，其中 `dp[i]` 表示拼凑 `i` 金额所需的最少硬币数。
 *    - 递归公式：
 *      ```
 *      dp(amount) = min(dp(amount - coin) + 1)  (for each coin in coins)
 *      ```
 * 2️⃣ **使用记忆化数组 `count[]`**
 *    - 避免重复计算，存储已计算的 `dp[i]` 值，加速计算。
 *    - 递归终止条件：
 *      - `amount == 0`：返回 `0`（无需任何硬币）。
 *      - `amount < 0`：返回 `-1`（无法拼凑）。
 *
 * **示例**
 * ```
 * coins = [1, 2, 5], amount = 11
 * 递归调用：
 * dp(11) = min(dp(10), dp(9), dp(6)) + 1
 * dp(10) = min(dp(9), dp(8), dp(5)) + 1
 * ...
 * 最终得到 dp(11) = 3
 * ```
 * **时间复杂度：O(amount * coins.length)**
 * **空间复杂度：O(amount)**
 *
 * ------------------------------------------------------
 * **方法 2：自底向上 DP（Bottom-Up DP）**
 * ------------------------------------------------------
 * 1️⃣ **定义 `dp[i]`**：
 *    - `dp[i]` 表示拼凑金额 `i` 所需的最少硬币数。
 *    - 初始化 `dp[i] = amount + 1`（表示无解）。
 *    - `dp[0] = 0`（拼凑 `0` 需要 `0` 个硬币）。
 *
 * 2️⃣ **状态转移方程**
 *    ```
 *    dp[i] = min(dp[i], dp[i - coin] + 1)  (for each coin in coins)
 *    ```
 * 3️⃣ **遍历顺序**
 *    - `i` 从 `1` 到 `amount` 遍历。
 *    - `coin` 遍历 `coins[]`，计算 `dp[i]` 的最优解。
 *
 * **示例**
 * ```
 * coins = [1, 2, 5], amount = 11
 * 初始化 dp[] = [0, ∞, ∞, ∞, ∞, ∞, ∞, ∞, ∞, ∞, ∞, ∞]
 * 遍历 i=1: dp[1] = min(dp[1], dp[0]+1) = 1
 * 遍历 i=2: dp[2] = min(dp[2], dp[1]+1) = 1
 * 遍历 i=5: dp[5] = min(dp[5], dp[0]+1) = 1
 * 计算最终 dp[11] = 3
 * ```
 * **时间复杂度：O(amount * coins.length)**
 * **空间复杂度：O(amount)**
 *
 * ------------------------------------------------------
 * **方法 3：二维 DP（完全背包问题解法）**
 * ------------------------------------------------------
 * 1️⃣ **定义 `dp[i][j]`**
 *    - `dp[i][j]` 表示 **使用前 `i` 种硬币**，拼凑金额 `j` 的最少硬币数。
 *    - `dp[i][j] = min(dp[i-1][j], dp[i][j - coins[i-1]] + 1)`
 *
 * 2️⃣ **状态转移**
 *    - 如果 `coins[i-1] > j`，不能选 `coins[i-1]`，则 `dp[i][j] = dp[i-1][j]`
 *    - 否则：可以选或不选：
 *      ```
 *      dp[i][j] = min(dp[i-1][j], dp[i][j - coins[i-1]] + 1)
 *      ```
 *
 * **示例**
 * ```
 * coins = [1, 2, 5], amount = 11
 * 初始化 DP 矩阵：
 *     0  1  2  3  4  5  6  7  8  9 10 11
 *  1  0  1  2  3  4  5  6  7  8  9 10 11
 *  2  0  1  1  2  2  3  3  4  4  5  5  6
 *  5  0  1  1  2  2  1  2  2  3  3  2  3
 * 最终答案：dp[3][11] = 3
 * ```
 * **时间复杂度：O(n * amount)**
 * **空间复杂度：O(n * amount)**
 */

/*
 * 时间和空间复杂度分析：
 *
 * **方法 1（递归 + 记忆化搜索，Top-Down DP）**
 * - **时间复杂度：O(amount * coins.length)**（每个子问题最多被计算一次）
 * - **空间复杂度：O(amount)**（递归深度最多为 `amount`）
 *
 * **方法 2（自底向上 DP，Bottom-Up DP）**
 * - **时间复杂度：O(amount * coins.length)**（遍历 `amount` 次，每次遍历 `coins`）
 * - **空间复杂度：O(amount)**（使用一维 `dp[]` 数组存储结果）
 *
 * **方法 3（二维 DP，完全背包）**
 * - **时间复杂度：O(n * amount)**（遍历 `n` 种硬币，每种硬币遍历 `amount` 次）
 * - **空间复杂度：O(n * amount)**（使用二维 `dp[][]` 数组存储结果）
 *
 * **推荐选择**
 * | 方法 | 时间复杂度 | 空间复杂度 | 适用场景 |
 * |------|----------|----------|--------|
 * | **递归 + 记忆化搜索** | `O(amount * n)` | `O(amount)` | 适用于小 `amount`，但递归可能导致栈溢出 |
 * | **自底向上 DP** | `O(amount * n)` | `O(amount)` | **最优解**，适用于大规模 `amount` |
 * | **二维 DP** | `O(n * amount)` | `O(n * amount)` | 适用于学习背包问题的动态规划 |
 */

public class LeetCode_322_CoinChange {

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {

        // Solution 1: 递归 + 记忆化搜索（自顶向下的动态规划）
        public int coinChange1(int[] coins, int amount) {
            if (amount < 1) return 0; // 金额为 0 时，直接返回 0
            return coinChange(coins, amount, new int[amount]); // 递归计算最优解
        }

        // 递归函数：计算组成金额 rem 所需的最少硬币数
        private int coinChange(int[] coins, int rem, int[] count) {
            if (rem < 0) return -1; // 如果金额小于 0，说明无法组成，返回 -1
            if (rem == 0) return 0; // 如果金额正好为 0，则不需要任何硬币，返回 0
            if (count[rem - 1] != 0) return count[rem - 1]; // 如果之前计算过，直接返回缓存值

            int min = Integer.MAX_VALUE;
            for (int coin : coins) { // 遍历所有硬币
                int res = coinChange(coins, rem - coin, count); // 递归计算剩余金额所需的最小硬币数
                if (res >= 0 && res < min) {
                    min = 1 + res; // 选择最优方案，当前硬币 + 子问题的解
                }
            }

            count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min; // 缓存结果
            return count[rem - 1];
        }

        // Solution 2: 自底向上的动态规划（状态转移方程：dp[i] = min(dp[i], dp[i - coin] + 1)）
        public int coinChange2(int[] coins, int amount) {
            int max = amount + 1;
            int[] dp = new int[amount + 1];
            Arrays.fill(dp, max); // 初始化所有 dp[i] 为一个不可能的最大值
            dp[0] = 0; // 初始条件，金额为 0 时不需要硬币

            for (int i = 1; i <= amount; i++) { // 遍历每个金额
                for (int j = 0; j < coins.length; j++) { // 遍历每个硬币
                    if (coins[j] <= i) {
                        dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1); // 取最小值
                    }
                }
            }
            return dp[amount] > amount ? -1 : dp[amount]; // 如果金额无法组成，返回 -1
        }

        // Solution 3: 二维动态规划 (DP)（完全背包问题解法）`dp[i][j]` 表示 **使用前 `i` 种硬币**，拼凑金额 `j` 的最少硬币数
        public int coinChange(int[] coins, int amount) {
            int n = coins.length;
            int[][] dp = new int[n + 1][amount + 1];
            int maxs = amount + 1;

            // 初始化 DP 数组：dp[i][0] = 0（金额为 0 时硬币数为 0）
            for (int i = 0; i <= n; i++) {
                dp[i][0] = 0;
            }

            // 初始化 DP 数组：dp[0][j] = maxs（没有硬币时，所有金额都无法组成）
            for (int j = 1; j <= amount; j++) {
                dp[0][j] = maxs;
            }

            for (int c = 1; c <= coins.length; c++) {
                for (int a = 1; a <= amount; a++) {
                    if (coins[c - 1] > a) { // 当前硬币值大于金额
                        dp[c][a] = dp[c - 1][a]; // 不能选当前硬币
                    } else {
                        dp[c][a] = Math.min(dp[c - 1][a], dp[c][a - coins[c - 1]] + 1); // 选或不选当前硬币
                    }
                }
            }

            return dp[n][amount] >= maxs ? -1 : dp[n][amount]; // 如果 dp[n][amount] 仍为 maxs，说明无法组成该金额
        }

        // 打印 DP 矩阵（调试用）
        private void printMatrix(int[][] matrix) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_322_CoinChange().new Solution();

        // 测试用例 1
        int[] coins1 = {1, 2, 5};
        int amount1 = 11;
        System.out.println("Solution 1 (Memoized Recursion): " + solution.coinChange1(coins1, amount1));  // 预期输出: 3
        System.out.println("Solution 2 (Bottom-up DP): " + solution.coinChange2(coins1, amount1));  // 预期输出: 3
        System.out.println("Solution 3 (2D DP): " + solution.coinChange(coins1, amount1));  // 预期输出: 3

        // 测试用例 2
        int[] coins2 = {2};
        int amount2 = 3;
        System.out.println("Solution 1 (Memoized Recursion): " + solution.coinChange1(coins2, amount2));  // 预期输出: -1
        System.out.println("Solution 2 (Bottom-up DP): " + solution.coinChange2(coins2, amount2));  // 预期输出: -1
        System.out.println("Solution 3 (2D DP): " + solution.coinChange(coins2, amount2));  // 预期输出: -1

        // 测试用例 3
        int[] coins3 = {1};
        int amount3 = 0;
        System.out.println("Solution 1 (Memoized Recursion): " + solution.coinChange1(coins3, amount3));  // 预期输出: 0
        System.out.println("Solution 2 (Bottom-up DP): " + solution.coinChange2(coins3, amount3));  // 预期输出: 0
        System.out.println("Solution 3 (2D DP): " + solution.coinChange(coins3, amount3));  // 预期输出: 0
    }
}


/**
You are given an integer array coins representing coins of different 
denominations and an integer amount representing a total amount of money. 

 Return the fewest number of coins that you need to make up that amount. If 
that amount of money cannot be made up by any combination of the coins, return -1. 

 You may assume that you have an infinite number of each kind of coin. 

 
 Example 1: 

 
Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
 

 Example 2: 

 
Input: coins = [2], amount = 3
Output: -1
 

 Example 3: 

 
Input: coins = [1], amount = 0
Output: 0
 

 
 Constraints: 

 
 1 <= coins.length <= 12 
 1 <= coins[i] <= 2³¹ - 1 
 0 <= amount <= 10⁴ 
 

 Related Topics Array Dynamic Programming Breadth-First Search 👍 18272 👎 426

*/