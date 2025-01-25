package leetcode.question.dp;

import java.util.Arrays;

/**
 *@Question:  518. Coin Change II
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 59.13%
 *@Time  Complexity: O(N*amount) for solution 1, solution 2 and solution 3
 *@Space Complexity: O(N*amount) for solution 1 and solution 2, O(amount) for solution 3
 */

/**
 * ### 解题思路
 *
 * 本题目要求找出可以用给定的硬币面值组成指定金额的所有不同组合的数量。我们可以通过动态规划的方法来解决这个问题，提供了三种不同的解法：
 *
 * #### 1. 递归+记忆化搜索（Top-down DP）
 *
 * **思路：**
 * - 使用递归的方法，尝试每一种可能的组合，同时利用备忘录保存已经计算过的结果，避免重复计算。
 * - 定义一个递归函数`numberOfWays(i, amount)`表示用从第`i`种硬币开始的硬币可以组成金额`amount`的方法数。
 * - 对于每个硬币，我们有两种选择：
 *   1. 不使用这个硬币，直接跳到下一种硬币，即`numberOfWays(i + 1, amount)`。
 *   2. 使用这个硬币，则递归调用`numberOfWays(i, amount - coins[i])`。
 * - 将上述两种情况的结果相加得到总的组合数。
 *
 * **时间复杂度：** O(N * amount)，其中 N 是硬币的数量，amount 是目标金额。每个状态只计算一次。
 *
 * **空间复杂度：** O(N * amount)，用于存储备忘录。
 *
 * #### 2. 动态规划（Bottom-up DP）
 *
 * **思路：**
 * - 使用二维数组`dp`，其中`dp[i][j]`表示使用前`i`种硬币可以组成金额`j`的方法数。
 * - 初始化`dp`数组，`dp[i][0] = 1`，因为金额为0时只有一种组合方式，即不选择任何硬币。
 * - 对于每种硬币和每个可能的金额，计算包括当前硬币和不包括当前硬币的组合数。
 * - 最终结果为`dp[0][amount]`。
 *
 * **时间复杂度：** O(N * amount)，因为需要遍历所有硬币和所有可能的金额。
 *
 * **空间复杂度：** O(N * amount)，用于存储动态规划数组。
 *
 * #### 3. 空间优化的动态规划
 *
 * **思路：**
 * - 使用一维数组`dp`，其中`dp[j]`表示组成金额`j`的方法数。
 * - 初始化`dp`数组，`dp[0] = 1`，因为金额为0时只有一种组合方式，即不选择任何硬币。
 * - 对于每种硬币，更新`dp`数组，其中`dp[j] += dp[j - coins[i]]`。
 * - 最终结果为`dp[amount]`。
 *
 * **时间复杂度：** O(N * amount)，因为需要遍历所有硬币和所有可能的金额。
 *
 * **空间复杂度：** O(amount)，只需一维数组存储中间结果。
 *
 * ### 总结
 *
 * - **时间复杂度：** 三种解法的时间复杂度都是 O(N * amount)。
 * - **空间复杂度：**
 *   - 递归+记忆化搜索和动态规划的空间复杂度为 O(N * amount)。
 *   - 空间优化的动态规划的空间复杂度为 O(amount)。
 *
 * 这三种方法各有优缺点，递归+记忆化搜索适合理解递归思路，动态规划方法适合较为直观的底向上构建解，空间优化的动态规划适合在空间受限的情况下使用。
 */
public class LeetCode_518_CoinChangeIi{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int[][] memo;  // 备忘录，用于记忆化搜索
        int[] coins;   // 硬币数组

        //Solution 1: Top-down DP
        public int numberOfWays(int i, int amount) {
            if (amount == 0) {
                return 1;  // 如果金额为0，只有一种方式，即不选择任何硬币
            }
            if (i == coins.length) {
                return 0;  // 如果没有更多硬币可选，则没有任何组合
            }
            if (memo[i][amount] != -1) {
                return memo[i][amount];  // 如果已经计算过，直接返回
            }

            if (coins[i] > amount) {
                return memo[i][amount] = numberOfWays(i + 1, amount);  // 当前硬币面值大于剩余金额，跳过当前硬币
            }

            // 计算包括当前硬币和不包括当前硬币的两种情况
            memo[i][amount] = numberOfWays(i, amount - coins[i]) + numberOfWays(i + 1, amount);
            return memo[i][amount];
        }

        public int change1(int amount, int[] coins) {
            this.coins = coins;  // 初始化硬币数组
            memo = new int[coins.length][amount + 1];  // 初始化备忘录
            for (int[] row : memo) {
                Arrays.fill(row, -1);  // 将备忘录初始化为-1，表示尚未计算
            }

            return numberOfWays(0, amount);  // 计算从第一个硬币开始的组合数
        }

        //Solution 2: bottom up DP
        public int change2(int amount, int[] coins) {
            int n = coins.length;
            int[][] dp = new int[n + 1][amount + 1];
            for (int i = 0; i < n; i++) {
                dp[i][0] = 1;  // 金额为0时，只有一种组合方式，即不选择任何硬币
            }

            for (int i = 1; i <= amount; i++) {
                dp[0][i] = 0;  // 没有硬币时，无法组成非零金额
            }

            for (int c = n - 1; c >= 0; c--) {
                for (int j = 1; j <= amount; j++) {
                    if (coins[c] > j) {
                        dp[c][j] = dp[c + 1][j];  // 当前硬币面值大于剩余金额，跳过当前硬币
                    } else {
                        dp[c][j] = dp[c + 1][j] + dp[c][j - coins[c]];  // 包括和不包括当前硬币的两种情况
                    }
                }
            }

            return dp[0][amount];  // 返回结果
        }

        //Solution 3: bottom up DP + space optimization
        public int change(int amount, int[] coins) {
            int n = coins.length;
            int[] dp = new int[amount + 1];
            dp[0] = 1;  // 金额为0时，只有一种组合方式，即不选择任何硬币

            for (int i = n - 1; i >= 0; i--) {
                for (int j = coins[i]; j <= amount; j++) {
                    dp[j] += dp[j - coins[i]];  // 更新dp数组，包含当前硬币
                }
            }

            return dp[amount];  // 返回结果
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_518_CoinChangeIi().new Solution();

        // 测试样例
        int amount = 5;
        int[] coins = {1, 2, 5};

        // 调用三种解法
        System.out.println("Solution 1: " + solution.change1(amount, coins)); // 输出3
        System.out.println("Solution 2: " + solution.change2(amount, coins)); // 输出3
        System.out.println("Solution 3: " + solution.change(amount, coins));  // 输出3
    }
}

/**
 You are given an integer array coins representing coins of different
 denominations and an integer amount representing a total amount of money.

 Return the number of combinations that make up that amount. If that amount of
 money cannot be made up by any combination of the coins, return 0.

 You may assume that you have an infinite number of each kind of coin.

 The answer is guaranteed to fit into a signed 32-bit integer.


 Example 1:


 Input: amount = 5, coins = [1,2,5]
 Output: 4
 Explanation: there are four ways to make up the amount:
 5=5
 5=2+2+1
 5=2+1+1+1
 5=1+1+1+1+1


 Example 2:


 Input: amount = 3, coins = [2]
 Output: 0
 Explanation: the amount of 3 cannot be made up just with coins of 2.


 Example 3:


 Input: amount = 10, coins = [10]
 Output: 1



 Constraints:


 1 <= coins.length <= 300
 1 <= coins[i] <= 5000
 All the values of coins are unique.
 0 <= amount <= 5000


 Related Topics Array Dynamic Programming 👍 9041 👎 159

 */